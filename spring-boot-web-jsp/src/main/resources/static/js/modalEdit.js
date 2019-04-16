// Get the modal


// When the user clicks the button, open the modal
$(".editbutton").click(function(){
    var id = $(this).data('id');
    var name = $(this).data('name');
    var login = $(this).data('login');
    var pass = $(this).data('password');



    $("#id").attr("value", id);
    $("#name").attr("value", name);
    $("#login").attr("value", login);
    $("#password").attr("value", pass);
    $("#modalEdit").css("display", "block");

});



$(".buttonclose").click(function(){
    $("#modalEdit").css("display", "none")});
// When the user clicks on <span> (x), close the modal
// When the user clicks anywhere outside of the modal, close it
