<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台模板</title>
    <link rel="stylesheet" href="/statics/assets/css/amazeui.css" />
    <link rel="stylesheet" href="/statics/assets/css/core.css" />
    <link rel="stylesheet" href="/statics/assets/css/menu.css" />
    <link rel="stylesheet" href="/statics/assets/css/admin.css" />
    <link rel="stylesheet" href="/statics/assets/css/page/typography.css" />
    <link rel="stylesheet" href="/statics/assets/css/page/form.css" />
    <link rel="stylesheet" href="/statics/assets/css/component.css" />
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/statics/css/toastr.css" />
    <link rel="stylesheet" href="/statics/css/bootstrap-tab.css" />
    <script type="text/javascript" src="/statics/assets/js/jquery-2.1.0.js" ></script>
    <script type="text/javascript" src="/statics/js/jquery.min.js" ></script>
    <script type="text/javascript" src="/statics/assets/js/amazeui.min.js"></script>
    <script type="text/javascript" src="/statics/assets/js/blockUI.js" ></script>
    <script type="text/javascript" src="/statics/bootstrap/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="/statics/js/toastr.js" ></script>
    <script type="text/javascript" src="/statics/js/BootstrapMenu.min.js" ></script>
    <script type="text/javascript" src="/statics/js/bootstrap-tab.js" ></script>
</head>
<body>

<div class="admin" style="margin-top: 0px;">
    <div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas" style="margin-top: 0px">
        <div class="am-offcanvas-bar admin-offcanvas-bar" >
            <div class="user-box am-hide-sm-only">
                <div class="user-img">
                    <img src="/statics/assets/img/avatar-1.jpg" alt="user-img" title="Mat Helme" class="img-circle img-thumbnail img-responsive">
                    <div class="user-status offline"><i class="am-icon-dot-circle-o" aria-hidden="true"></i></div>
                </div>

                <ul class="list-inline">
                    <li>
                        <a href="#">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="text-custom">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- End User -->
            <ul class="am-list admin-sidebar-list" id="ztree" >
            </ul>
        </div>
    </div>
                <div  style="padding-left:5px; width:100%;height: 100%">
                    <ul class="nav nav-tabs" role="tablist" id="tt">
                            <li class="active" id="home">
                                <a href="#Index" role="tab" data-toggle="tab"><i class="glyphicon glyphicon-home"></i>&nbsp;&nbsp;首&nbsp;页</a>
                            </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="Index" style="width:100%;height: 100%;">
                            <img src="/statics/images/paragliding.jpg" style="width: 99%; height: 100%" >
                        </div>
                    </div>
                </div>
</div>
</div>


</body>
</html>
