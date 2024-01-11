<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<form method="post" action="/homeworkSpringMVC/update" enctype="multipart/form-data">
<form class="row g-3">
<div class="col-md-4">
         <label for="validationDefaultUsername" class="form-label">Введите Логин</label>
         <input type="text" class="form-control" id="login" name="login"  required>
     </div>
  <div class="col-md-4">
    <label for="validationDefault01" class="form-label">Имя</label>
    <input type="text" class="form-control" name="name" id="name"  required>
  </div>
  <div class="col-md-4">
    <label for="validationDefault02" class="form-label">Фамилия</label>
    <input type="text" class="form-control" id="surname" name="surname"  required>
  </div>


    <div class="col-12">
    <button class="btn btn-primary" type="submit">Submit form</button>
  </div>
</form>