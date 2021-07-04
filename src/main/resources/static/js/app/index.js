var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-join').on('click',function (){
            _this.join();
        });

        $('#btn-comment').on('click',function (){
            _this.comment();
        });

        $('#btn-password-change').on('click',function (){
            _this.passwordChange();
        })

        $('#content').summernote({
            height:250,
        });

        $('#search').on('change',function () {
            $('#query').attr('name',$('#search option:selected').val());
        })
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').text();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    comment: function (){
        var data = {
            content: $('#comment').val()
        };

        var id = $('#id').text();

        console.log(id);

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/'+id +'/comments',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('댓글이 작성되었습니다.');
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').text();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    comment_delete : function (id){
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/comments/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('댓글이 삭제되었습니다.');
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    join : function () {
        var data = {
            username: $('#username').val(),
            password: $('#password').val(),
        };


        $.ajax({
            type: 'POST',
            url: '/api/v1/user/sign-up',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('가입 되었습니다');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    passwordChange : function (){
        var data = {
            curPassword: $('#curPassword').val(),
            newPassword: $('#newPassword').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/user/password',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function() {
            alert("비밀번호가 변경되었습니다. 다시 로그인해주세요.");
            window.location.href = '/logout';
        }).fail(function()  {
            alert("현재 비밀번호가 일치하지 않습니다.");
            window.location.reload();
        });
    }

};

main.init();
