package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.CategoryDto;
import com.digitalrangersbd.DeepManage.Dto.CategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Category;
import com.digitalrangersbd.DeepManage.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Create new category
    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto dto){

        try{
            Category createdCategory = categoryService.createCategory(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    //View all category
    @GetMapping("/view")
    public ResponseEntity<List<Category>> getCategory(){

        try{
            return ResponseEntity.ok(categoryService.getCategory());
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View category by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){

        try{
            return ResponseEntity.of(categoryService.getCategoryById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update category
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDto dto){

        try{
            Category category = categoryService.updateCategory(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(category);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){


        try{
            Category deletedCategory = categoryService.deleteCategory(id);

            if(deletedCategory != null){
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
}