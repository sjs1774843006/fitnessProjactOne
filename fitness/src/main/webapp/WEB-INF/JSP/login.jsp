<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="icon" href="/statics/images/bitbug_favicon.ico" type="image/x-icon"/>
    <title>登录界面</title>
    <link href="../../statics/layui/test/default.css" rel="stylesheet" type="text/css" />
    <!--必要样式-->
    <link href="../../statics/layui/test/styles.css" rel="stylesheet" type="text/css" />
    <link href="../../statics/layui/test/demo.css" rel="stylesheet" type="text/css" />
    <link href="../../statics/layui/test/loaders.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class='login'>
    <div class='login_title'>
        <!-- 	    <span>小额借贷管理系统</span> -->
    </div>
    <div class='login_fields'>
        <div class='login_fields__user'>
            <div class='icon'>
                <img alt="" src='../../statics/layui/img/user_icon_copy.png'>
            </div>
            <input name="login" placeholder='用户名' maxlength="16" type='text' autocomplete="off" value=""/>
            <div class='validation'>
                <img alt="" src='../../statics/layui/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='../../statics/layui/img/lock_icon_copy.png'>
            </div>
            <input name="pwd" placeholder='密码' maxlength="16" type='text' autocomplete="off">
            <div class='validation'>
                <img alt="" src='../../statics/layui/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='../../statics/layui/img/key.png'>
            </div>
            <input name="code" placeholder='验证码' maxlength="4" type='text' name="ValidateNum" autocomplete="off">
            <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
                <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
            </div>
        </div>
        <div class='login_fields__submit'>
            <input type='button' value='登录'>
        </div>
    </div>
    <div class='success'>
    </div>
    <div class='disclaimer'>
        <p>欢迎登陆后台管理系统</p>
    </div>
</div>
<div class='authent'>
    <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>登录中...</p>
</div>
<div class="OverWindows"></div>
<link href="../../statics/layui/css/layui.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../../statics/layui/js/jquery-ui.min.js"></script>
<script type="text/javascript" src='../../statics/layui/js/stopExecutionOnTimeout.js?t=1'></script>
<script src="../../statics/layui/layui.js" type="text/javascript"></script>
<script src="../../statics/layui/js/Particleground.js" type="text/javascript"></script>
<script src="../../statics/layui/js/Treatment.js" type="text/javascript"></script>
<script src="../../statics/layui/js/jquery.mockjax.js" type="text/javascript"></script>

<script type="text/javascript">
    var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
    var ajaxmockjax = 1;//是否启用虚拟Ajax的请求响 0 不启用  1 启用
    //默认账号密码

    var CodeVal = 0;
    Code();
    function Code() {
        if(canGetCookie == 1){
            createCode("AdminCode");
            var AdminCode = getCookieValue("AdminCode");
            showCheck(AdminCode);
        }else{
            showCheck(createCode(""));
        }
    }
    function showCheck(a) {
        CodeVal = a;
        var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");
        ctx.clearRect(0, 0, 1000, 1000);
        ctx.font = "80px 'Hiragino Sans GB'";
        ctx.fillStyle = "#E8DFE8";
        ctx.fillText(a, 0, 100);
    }
    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            $('input[type="button"]').click();
        }
    });
    //粒子背景特效
    $('body').particleground({
        dotColor: '#E8DFE8',
        lineColor: '#133b88'
    });
    $('input[name="pwd"]').focus(function () {
        $(this).attr('type', 'password');
    });
    $('input[type="text"]').focus(function () {
        $(this).prev().animate({ 'opacity': '1' }, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({ 'opacity': '.5' }, 200);
    });
    $('input[name="login"],input[name="pwd"]').keyup(function () {
        var Len = $(this).val().length;
        if (!$(this).val() == '' && Len >= 5) {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    var open = 0;


    layui.use('layer', function () {

        //非空验证
        $('input[type="button"]').click(function () {
            var login = $('input[name="login"]').val();
            var pwd = $('input[name="pwd"]').val();
            var code = $('input[name="code"]').val();

            if (login == '') {
                ErroAlert('请输入您的账号');
            } else if (pwd == '') {
                ErroAlert('请输入密码');
            } else if (code == '' || code.length != 4) {
                ErroAlert('输入验证码');
            } else if(code.toUpperCase() != CodeVal.toUpperCase()){
                ErroAlert('请输入正确验证码');
                $('input[name="code"]').val("")
                Code();
            } else {
                //认证中..
                $('.login').addClass('test'); //倾斜特效
                setTimeout(function () {
                    $('.login').addClass('testtwo'); //平移特效
                }, 300);
                setTimeout(function () {
                    $('.authent').show().animate({ right: -320 }, {
                        easing: 'easeOutQuint',
                        duration: 600,
                        queue: false
                    });
                    $('.authent').animate({ opacity: 1 }, {
                        duration: 200,
                        queue: false
                    }).addClass('visible');
                }, 500);

                $.ajax({
                    url:'loginsubmit.do',
                    method:'post',
                    dataType:'JSON',
                    data:"account="+login+"&passwrod="+pwd,
                    success:function(data){
                        if(data.success=="success"){
                            location.href='jsp.do';
                        }else if(data.success=="defeated"){
                            setTimeout(function () {
                                $('.authent').show().animate({ right: 90 }, {
                                    easing: 'easeOutQuint',
                                    duration: 600,
                                    queue: false
                                });
                                $('.authent').animate({ opacity: 0 }, {
                                    duration: 200,
                                    queue: false
                                }).addClass('visible');
                                $('.login').removeClass('testtwo'); //平移特效
                            }, 2000);
                            setTimeout(function () {
                                $('.authent').hide();
                                $('.login').removeClass('test');
                                ErroAlert('账号或密码错误,登录失败!');
                                $('input[name="code"]').val("")
                                Code();
                            }, 2400);
                        }
                        else if(data.success=="system"){
                            setTimeout(function () {
                                $('.authent').show().animate({ right: 90 }, {
                                    easing: 'easeOutQuint',
                                    duration: 600,
                                    queue: false
                                });
                                $('.authent').animate({ opacity: 0 }, {
                                    duration: 200,
                                    queue: false
                                }).addClass('visible');
                                $('.login').removeClass('testtwo'); //平移特效
                            }, 2000);
                            setTimeout(function () {
                                $('.authent').hide();
                                $('.login').removeClass('test');
                                ErroAlert('权限不足,登录失败!');
                                $('input[name="code"]').val("")
                                Code();
                            }, 2400);
                        }else if(data.success=="datatime"){
                            setTimeout(function () {
                                $('.authent').show().animate({ right: 90 }, {
                                    easing: 'easeOutQuint',
                                    duration: 600,
                                    queue: false
                                });
                                $('.authent').animate({ opacity: 0 }, {
                                    duration: 200,
                                    queue: false
                                }).addClass('visible');
                                $('.login').removeClass('testtwo'); //平移特效
                            }, 2000);
                            setTimeout(function () {
                                $('.authent').hide();
                                $('.login').removeClass('test');
                                ErroAlert('登录超时!请重试');
                                $('input[name="code"]').val("")
                                Code();
                            }, 2400);
                        }
                    }

                });

            }
        })
    })
</script>
</body>
</html>