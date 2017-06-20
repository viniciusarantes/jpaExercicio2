$('.del-animal').bind('click', deleteAnimal);

function changeSection(section) {
  $('.section').each(function() {
    $(this).addClass('hidden-section');
  });
  $('.'+section).removeClass('hidden-section');
};

function clearAnimais() {
  $('.animais tbody').find('tr').remove();
};

$('.btn-login').on('click', accessUser);
function accessUser(e) {
  e.preventDefault();
  $.ajax({
    method: "POST",
    url: "http:/localhost:8080/springRest/login",
    data: JSON.stringify({"username": $('#loginInput').val(), "password": $('#passwordInput').val()}),
    dataType: "json",
    contentType: "application/json"
  }).success(function(result, status, request) {
    localStorage.token = request.getResponseHeader("Token");
    getAllAnimal();
  }).error(function(result) {
    alert("Erro!")
  });
};

function getAllAnimal() {
  changeSection("animais");
  $.ajax({
    method: "GET",
    url: "http:/localhost:8080/springRest/animal/getAll",
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    clearAnimais();
    for (var i = 0; i < result.length; ++i) {
      $('.animais tbody').append('<tr class="active"><td>'+result[i].id+'</td>\
      <td>'+result[i].dono.nome+'</td>\
      <td>'+result[i].nome+'</td>\
      <td>'+result[i].especie+'</td>\
      <td>'+result[i].raca+'\
      <button type="button" class="close"><span aria-hidden="true" class="del-animal">×</span></button></td></tr>');
    }
  }).error(function(result) {
    alert("Erro!")
  });
};

function deleteAnimal(e) {
  console.log("maoi");
  e.preventDefault();
  $.ajax({
    method: "GET",
    url: "http:/localhost:8080/springRest/animal/delete/" + $(this).closest('tr').first().text(),
    dataType: "json",
    contentType: "application/json"
  }).success(function(result) {
    getAllAnimal();
  }).error(function(result) {
    alert("Erro!")
  });
};

$('.btn-search').on('click', function(e){
  e.preventDefault();
  changeSection("animais");
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/springRest/animal/getById?id=" + $('#search').val(),
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    clearAnimais();
    $('.animais tbody').append('<tr class="active"><td>'+result.id+'</td>\
    <td>'+result.dono.nome+'</td>\
    <td>'+result.nome+'</td>\
    <td>'+result.especie+'</td>\
    <td>'+result.raca+'</td></tr>');
  }).error(function(result) {
    alert("Erro!")
  });
});

$('.cadastro_animal').on('click', function(e){
  changeSection("form_animal");
});
$('.btn-cad-animal').on('click', function(e){
  e.preventDefault();
  $.ajax({
    method: "POST",
    url: "http://localhost:8080/springRest/animal/salvar",
    data: JSON.stringify({"nome": $('#nomeInput').val(), "especie": $('#especieInput').val(), "raca": $('#racaInput').val(), "dono": {"id": $('#donoInput').val()} }),
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    alert("Salvo!")
  }).error(function(result) {
    alert("Erro!")
  });
});

$('.cadastro_dono').on('click', function(e){
  changeSection("form_dono");
});
$('.btn-cad-dono').on('click', function(e){
  e.preventDefault();
  $.ajax({
    method: "POST",
    url: "http://localhost:8080/springRest/dono/salvar",
    data: JSON.stringify({"id": localStorage.userId, "nome": $('#nomeDonoInput').val(), "senha": $('#passwordInput').val(), "endereco": $('#enderecoInput').val(), "telefone": $('#telefoneInput').val()}),
    dataType: "json",
    contentType: "application/json"
  }).success(function(result) {
    alert("Salvo!")
  }).error(function(result) {
    alert("Erro!")
  });
});
