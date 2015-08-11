<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String authUser = (String)request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会管理系统</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/plugins/echarts/echarts.js"></script>
		<script src="<%=basePath%>static/console/manager/dashboard.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<jsp:include page="nav.jsp">
				<jsp:param value="manage_dashboard" name="page_index"/>
			</jsp:include>		
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
						$(document).ready(function(){
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							width = $(".page-content").width();
							$('#visit_chart').css('width',width);
							$('#visit_chart').css('height',400);
							// 配置路径  
					        require.config({  
					            paths: {  
					                echarts: base+'static/plugins/echarts'  
					            }  
					        });  
					        // 按需加载  
					        require(  
					            [  
					                'echarts',   
					                'echarts/chart/bar',  
					                'echarts/chart/line'  
					            ],  
					            function (ec) {  
					                var chart = document.getElementById('visit_chart');  
					                var echart = ec.init(chart);  
					                  
					                echart.showLoading({  
					                    text: '正在努力加载中...'  
					                });  
					                  
					                var date = [];  
					                var pv = [];  
					                var uv = [];  
					                // 同步执行  
					                $.ajaxSettings.async = false;  
					                  
					                // 加载数据  
					                $.getJSON(base+'dashboard/visitCount', function (json) {  
					                    date = json.dates;  
					                    pv = json.pvs;  
					                    uv = json.uvs;  
					                });  
					                  
					                var option = {  
					                    tooltip: {  
					                        show: true  
					                    },  
					                    legend: {  
					                        data: ['PV访问量','UV访问量']  
					                    },  
					                    xAxis: [  
					                        {  
					                            type: 'category',  
					                            data: date  
					                        }  
					                    ],  
					                    yAxis: [  
					                        {  
					                            type: 'value'  
					                        }  
					                    ],  
					                    series: [  
					                        {  
					                            'name': 'PV',  
					                            'type': 'line',  
					                            'data': pv  
					                        },
					                        {  
					                            'name': 'UV',  
					                            'type': 'line',  
					                            'data': uv  
					                        }
					                    ]  
					                };  
					                echart.setOption(option);  
					                echart.hideLoading();  
					            }  
					        );
						});
							  
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="<%=basePath%>manage/index.html"><strong>首页</strong></a>
							</li>
							<li class="active"><b>数据纵览</b></li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="well well-sm">
									<button class="btn btn-sm btn-danger" onclick="jump2addProfessor()"> 
										<i class="ace-icon fa fa-pencil-square-o bigger-125"></i>
										<b>访问统计</b>
									</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div class="well well-sm" id="visit_chart"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
