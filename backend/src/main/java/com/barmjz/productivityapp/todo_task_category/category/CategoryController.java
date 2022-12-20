package com.barmjz.productivityapp.todo_task_category.category;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<Long> createCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> editCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        categoryService.editCategory(categoryId, category);
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        // what is the deletion responsibility ??
        // on deleting cascade
        categoryService.deleteCategory(categoryId);
        return null;
    }
}
