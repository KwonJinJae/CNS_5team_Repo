$(function() {
    $('#pass2').blur(function() {
        if($('#pass1').val() != $('#pass2').val()) {
            if($('#pass2').val() != '') {
                $('.pass-error').empty();
                $('.pass-error').append('<span>비밀번호가 일치하지 않습니다.</span>')
                $('#pass2').val('');
                $('#pass1').focus();
            }
        }
    })
})

$(function() {
    const id = $('#id').val();
    $('#id').blur(function() {
        $.ajax({
            type: "GET",
            url: '/signUpProc/' + id + '/exists'

        })
    })
})
$.ajax({
    type: "GET",
    url: '/signUpProc/'+'''/exists'
})