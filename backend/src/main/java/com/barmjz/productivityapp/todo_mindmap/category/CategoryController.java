package com.barmjz.productivityapp.todomindmap.category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/category")
public class CategoryController {

    @PostMapping("/")
    public ResponseEntity<Long> createCategory(@RequestBody Category category) {
        return null;
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> editCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        // what is the deletion responsibility ??
        // on deleting cascade
        return null;
    }
}
