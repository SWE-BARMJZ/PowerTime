package com.barmjz.productivityapp.todo_task_category.category;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryDTO> getCategories() {
        return categoryService.getAllUserCategories()
                .stream()
                .map(category -> new CategoryDTO(category.getId(), category.getCategory_name()))
                .toList();
    }

    @PostMapping("/")
    public ResponseEntity<Long> createCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> editCategory(@PathVariable Long categoryId, @RequestBody String category) {
        try {
            return ResponseEntity.ok(categoryService.editCategory(categoryId, category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
