# ๐ข ์คํ๋ง๋ถํธ ํต์ฌ๊ฐ์ด๋(JPA)

## ๋ชฉํ

### ์๊ตฌ ์ฌํญ

```
๐กย ๋ณ์ด์ ๋ฆฌ๋ทฐ๋ฅผ ๋ฌ ์ ์๋ API๋ฅผ ๋ง๋ค์
```

### ERD

![img.png](image/img.png)

## ํ์ด๋ธ ๋งคํ

### Hospital๊ณผ Review ํ์ด๋ธ ์๋ฐฉํฅ ๋งคํ

- Hospital ์ํฐํฐ ์ฝ๋

    ```java
    package com.jpa.study_springboot_jpa.hospital.domain.entity;
    
    import lombok.Getter;
    
    import javax.persistence.*;
    import java.util.List;
    
    @Entity
    @Table(name = "nation_wide_hospitals")
    @Getter
    public class Hospital {
        @Id
        private Integer id;
    
        private String hospitalName;
        private String roadNameAddress;
    
        @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
        private List<Review> reviews;
    }
    ```

- Review ์ํฐํฐ ์ฝ๋

    ```java
    package com.jpa.study_springboot_jpa.hospital.domain.entity;
    
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    
    import javax.persistence.*;
    
    @Entity
    @NoArgsConstructor
    @Getter
    public class Review {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String title;
        private String content;
        private String name;
    
        @ManyToOne
        @JoinColumn(name = "hospital_id")
        private Hospital hospital;
    
        public Review(String title, String content, String name, Hospital hospital) {
            this.title = title;
            this.content = content;
            this.name = name;
            this.hospital = hospital;
        }
    }
    ```

- ๋ณ์ ํ์ด๋ธ ์์ฅ์์ 1๊ฐ์ ๋ณ์์ ์ฌ๋ฌ๊ฐ์ ๋ฆฌ๋ทฐ๊ฐ ์์ ์ ์๊ธฐ ๋๋ฌธ์ OneToMany ๋งคํ์ด๋ค.
- ๋ฆฌ๋ทฐ ํ์ด๋ธ ์์ฅ์ ๋ณ์ ์์ฅ๊ณผ ๋ฐ๋์ด๊ธฐ ๋๋ฌธ์ ManyToOne ๋งคํ์ด๋ค.
- `fetch = FetchType.LAZY`
    - ๋ณ์์ ๋ฆฌ๋ทฐ ์ ๋ณด๋ ๋ง์ ์ ์๋ค.
    - ๋ณ์ ์ ๋ณด๋ฅผ ๋ถ๋ฌ์ฌ ๋๋ง๋ค ๋ฆฌ๋ทฐ ์ ๋ณด๋ฅผ ๋ถ๋ฌ์ค๋ ๊ฒ์ ๋นํจ์จ
    - ๋ฆฌ๋ทฐ ๋ฐ์ดํฐ๋ฅผ ๋ถ๋ฌ์ฌ ๋๋ง Join์ ํ๋ค.
    - ์ด๊ฒ์ ์ง์ฐ ๋ก๋ฉ์ด๋ผ๊ณ  ํ๋ค.

## ๋ฆฌ๋ทฐ ๋ฑ๋ก ๊ธฐ๋ฅ

- ๋ณ์ ๋ฆฌ๋ทฐ ๋ฑ๋ก ์๋ ํฌ์ธํธ
    - [http://localhost:8080/api/v1/hospital/1/review](http://localhost:8080/api/v1/hospital/1/review)

![img_2.png](image/img_2.png)
๊ตฌํ ๊ฒฐ๊ณผ

### ReviewRequest DTO ์ฝ๋

```java
package com.jpa.study_springboot_jpa.hospital.domain.dto;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Hospital;
import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
import lombok.Getter;

@Getter
public class ReviewRequest {
    private String title;
    private String content;
    private String name;

    public Review toEntity(Hospital hospital){
        return new Review(this.title,this.content,this.name,hospital);
    }
}
```

### ReviewResponse DTO ์ฝ๋

```java
package com.jpa.study_springboot_jpa.hospital.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewResponse {
    private Integer id;
    private Integer hospitalId;
    private String title;
    private String content;
    private String name;

    public static ReviewResponse of(Integer id, Integer hospitalId, String title, String content, String name){
        return new ReviewResponse(id,hospitalId,title,content,name);
    }
}
```

### ReviewRepository ์ฝ๋

```java
package com.jpa.study_springboot_jpa.hospital.repository;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
```

### ReviewService ์ฝ๋

```java
package com.jpa.study_springboot_jpa.hospital.service;

import com.jpa.study_springboot_jpa.hospital.domain.dto.ReviewRequest;
import com.jpa.study_springboot_jpa.hospital.domain.dto.ReviewResponse;
import com.jpa.study_springboot_jpa.hospital.domain.entity.Hospital;
import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
import com.jpa.study_springboot_jpa.hospital.repository.HospitalRepository;
import com.jpa.study_springboot_jpa.hospital.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewResponse saveReview(Integer id, ReviewRequest reivewRequest){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty())
            return null;
        Review review = reivewRequest.toEntity(optionalHospital.get());
        review = reviewRepository.save(review);
        return ReviewResponse.of(review.getId(),review.getHospital().getId(),review.getTitle(),review.getContent(),review.getName());
    }
}
```
## ๋ชฉํ

```
๐กย ๋ฆฌ๋ทฐ ์กฐํ ๊ธฐ๋ฅ์ ๊ตฌํํด๋ณด์.
```

## 1๊ฐ ๋ฆฌ๋ทฐ ์กฐํ ๊ธฐ๋ฅ

![img_1.png](image/img_1.png)

- 1๊ฐ ๋ณ์ ๋ฆฌ๋ทฐ ์กฐํ ์๋ํฌ์ธํธ
  - [http://localhost:8080/api/v1/review/7](http://localhost:8080/api/v1/review/7)

### ReviewService ํด๋์ค์ ๋ฉ์๋ ์ถ๊ฐ

```java
public ReviewResponse getReviewPage(Integer id){
    Optional<Review> optionalReview = reviewRepository.findById(id);
    if(optionalReview.isEmpty())
        return null;
    Review review = optionalReview.get();
    return ReviewResponse.of(review.getId(),review.getHospital().getId(),review.getTitle(),review.getContent(),review.getName());
}
```

- ๋ฆฌ๋ทฐ id๋ฅผ ํตํด 1๊ฐ์ ๋ฆฌ๋ทฐ๋ฅผ ๊ฐ์ ธ์ค๋ ๋ฉ์๋
- Optional์ ์ฌ์ฉ์ด์ 
  1. null์ ์ฐ์ง ์๊ธฐ ์ํด ์ฌ์ฉ
  2. DB์กฐํ ํ ๋ findById()๋ฅผ ์ธ๋ null์ด ๋์ฌ ์ ์๊ธฐ ๋๋ฌธ์ ์ฌ์ฉ
  3. ํธ์ ๊ธฐ๋ฅ์ ์ฌ์ฉํ๊ธฐ ์ํด ์ฌ์ฉ

### ReviewRestController ํด๋์ค ๊ตฌํ

```java
package com.jpa.study_springboot_jpa.hospital.controller;

import com.jpa.study_springboot_jpa.hospital.domain.dto.ReviewResponse;
import com.jpa.study_springboot_jpa.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> reviewPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(reviewService.getReviewPage(id));
    }
}
```

## ํด๋น ๋ณ์์ ๋ฆฌ๋ทฐ๋ง ์กฐํํ๋ ๊ธฐ๋ฅ

![img_4.png](image/img_4.png)

- ํน์  ๋ณ์์ ๋ฆฌ๋ทฐ ์กฐํ ์๋ ํฌ์ธํธ
  - [http://localhost:8080/api/v1/hospital/1/review](http://localhost:8080/api/v1/hospital/1/review)

### ReviewService ํด๋์ค ๋ฉ์๋ ์ถ๊ฐ

```java
public List<ReviewResponse> getReviewsPage(Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty())
            return null;
        List<Review> list = optionalHospital.get().getReviews();
        List<ReviewResponse> responses = new ArrayList<>();
        for (Review review: list) {
            ReviewResponse reviewResponse = ReviewResponse.of(review.getId(),review.getHospital().getId(),review.getTitle(),review.getContent(), review.getName());
            responses.add(reviewResponse);
        }
        return responses;
    }
```

### HospitalRestController ํด๋์ค ๋ฉ์๋ ์ถ๊ฐ

```java
@GetMapping("/{id}/review")
    public ResponseEntity<List<ReviewResponse>> hospitalReviewsPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(reviewService.getReviewsPage(id));
    }
```

## ๋ณ์ ์ ๋ณด์ ๋ฆฌ๋ทฐ ์กฐํ ๊ธฐ๋ฅ

![img_3.png](image/img_3.png)

- ๋ณ์ ์ ๋ณด์ ๋ฆฌ๋ทฐ ์กฐํ ๊ธฐ๋ฅ ์ํธ ํฌ์ธํธ
  - [http://localhost:8080/api/v1/hospital/1](http://localhost:8080/api/v1/hospital/1)

### HospitalResponse ํด๋์ค ๊ตฌํ

```java
package com.jpa.study_springboot_jpa.hospital.domain.dto;

import com.jpa.study_springboot_jpa.hospital.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private List<ReviewResponse> reviews;
    public static HospitalResponse of(Integer id, String hospitalName, String roadNameAddress, List<ReviewResponse> reviews){
        return new HospitalResponse(id,hospitalName,roadNameAddress,reviews);
    }

}
```

### HospitalSercice ํด๋์ค ๋ฉ์๋ ์ถ๊ฐ

```java
public HospitalResponse getHospitalPage(Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        HospitalResponse hospitalResponse = null;
        if(!optionalHospital.isEmpty()){
            Hospital hospital = optionalHospital.get();
            List<Review> list = hospital.getReviews();
            List<ReviewResponse> reviews = new ArrayList<>();
            for (Review review : list) {
                ReviewResponse reviewResponse = ReviewResponse.of(review.getId(),review.getHospital().getId(),review.getTitle(),review.getContent(),review.getName());
                reviews.add(reviewResponse);
            }
            hospitalResponse = HospitalResponse.of(hospital.getId(),hospital.getHospitalName(),hospital.getRoadNameAddress(),reviews);
        }
        return hospitalResponse;
    }
```

### HospitalRestController ๋ฉ์๋ ์ถ๊ฐ

```java
@GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> hospitalPage(@PathVariable Integer id){
        return ResponseEntity.ok().body(hospitalService.getHospitalPage(id));
    }
```