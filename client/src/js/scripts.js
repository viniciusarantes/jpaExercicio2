var LOCALHOST = "http://localhost:8080/trabalhoFinal/"

//$('.del-animal').bind('click', deleteAnimal);

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
    url: LOCALHOST+"login",
    data: JSON.stringify({"username": $('#loginInput').val(), "password": $('#passwordInput').val()}),
    dataType: "json",
    contentType: "application/json"
  }).success(function(result, status, request) {
    localStorage.token = request.getResponseHeader("Token");
    localStorage.userId = result.id;
    localStorage.userNome = result.nome;
    $("#nome_label").html(result.nome);
    $("#loginInput").val("");
    $("#passwordInput").val("");
    getAllAnimal();
  }).error(function(result) {
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
};

function getAllAnimal() {
  $.ajax({
    method: "GET",
    url: LOCALHOST+"animal/getAll",
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    changeSection("animais");
    clearAnimais();
    for (var i = 0; i < result.length; ++i) {
      $('.animais tbody').append('<tr class="active"><td>'+result[i].id+'</td>\
      <td>'+result[i].dono.nome+'</td>\
      <td>'+result[i].nome+'</td>\
      <td>'+result[i].especie+'</td>\
      <td>'+result[i].raca+'\
      <button type="button" class="close"><span aria-hidden="true" onclick="deleteAnimal('+result[i].id+')">×</span></button></td></tr>');
    }
  }).error(function(result) {
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      case 404:
        alert("Nenhum animal encontrado");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
};

function deleteAnimal(wId) {
  //e.preventDefault();
  $.ajax({
    method: "GET",
    url: LOCALHOST+"animal/delete/" + wId,//$(this).closest('tr').first().text(),
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    getAllAnimal();
    alert("Deletado!");
  }).error(function(result) {
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      case 404:
        alert("Nenhum animal encontrado");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
};

$('.btn-search').on('click', function(e){
  e.preventDefault();
  $.ajax({
    method: "GET",
    url: LOCALHOST+"animal/getById?id=" + $('#search').val(),
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    changeSection("animais");
    clearAnimais();
    for (var i = 0; i < result.length; ++i) {
      $('.animais tbody').append('<tr class="active"><td>'+result[i].id+'</td>\
      <td>'+result[i].dono.nome+'</td>\
      <td>'+result[i].nome+'</td>\
      <td>'+result[i].especie+'</td>\
      <td>'+result[i].raca+'\
      <button type="button" class="close"><span aria-hidden="true" onclick="deleteAnimal('+result[i].id+')">×</span></button></td></tr>');
    }
  }).error(function(result) {
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      case 404:
        alert("Nenhum animal encontrado");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
});

$('.cadastro_animal').on('click', function(e){
  $.ajax({
    method: "GET",
    url: LOCALHOST+"dono/getAll",
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    changeSection("form_animal");
    var html = "";
    for (i = 0; i < result.length; ++i) {
      html += '<option value="'+result[i].id+'">'+result[i].nome+'</option>';
    }
    $("#donoInput").html(html);
  }).error(function(result) { 
    console.log(result.status);
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      case 404:
        alert("Nenhum dono encontrado");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
});

$('.btn-cad-animal').on('click', function(e){
  e.preventDefault();
  $.ajax({
    method: "POST",
    url: LOCALHOST+"animal/salvar",
    data: JSON.stringify({"nome": $('#nomeInput').val(), "especie": $('#especieInput').val(), "raca": $('#racaInput').val(), "dono": {"id": $('#donoInput').val()} }),
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    $("#nomeInput").val("");
    $("#especieInput").val("");
    $("#racaInput").val("");
    getAllAnimal();
    alert("Salvo!");
  }).error(function(result) {
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
});

$('.cadastro_dono').on('click', function(e){
  $.ajax({
    method: "GET",
    url: LOCALHOST+"dono/getById?id="+localStorage.userId,
    dataType: "json",
    contentType: "application/json",
    headers: {"Authorization": localStorage.token}
  }).success(function(result) {
    changeSection("form_dono");
    $("#nomeDonoInput").val(result.nome);
    $("#enderecoInput").val(result.endereco);
    $("#telefoneInput").val(result.telefone);
  }).error(function(result) { 
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
});
$('.btn-cad-dono').on('click', function(e){
  e.preventDefault();
  $.ajax({
    method: "POST",
    url: LOCALHOST+"dono/salvar",
    data: JSON.stringify({"id": localStorage.userId, "nome": $('#nomeDonoInput').val(), "senha": $('#pwdDonoInput').val(), "endereco": $('#enderecoInput').val(), "telefone": $('#telefoneInput').val()}),
    dataType: "json",
    contentType: "application/json"
  }).success(function(result) {
    getAllAnimal();
    $("#pwdDonoInput").val("");
    alert("Salvo!")
  }).error(function(result) {
    switch(result.status) {
      case 0: 
        alert("Necessário efetuar o login!");
        changeSection("login");
        break;
      default:
        alert("Erro!");
        break;
    }
  });
});

$('.logout').on('click', function(e){
  if (localStorage.token != "") alert("Saiu do sistema!");
  localStorage.userNome = "";
  localStorage.userId = "";
  localStorage.token = "";
  $("#nome_label").html("");
  changeSection("login");
});

$('.inicio').on('click', function(e){
  checkUserLogged();
})

function checkUserLogged() {
  if (localStorage.token != "" && localStorage.userNome != "") {
    $("#nome_label").html(localStorage.userNome);
    getAllAnimal();
  }
  else {
    changeSection("login");
  }
}

checkUserLogged();
