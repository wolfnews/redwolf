jQuery(function($) {
	function showErrorAlert(reason, detail) {
		var msg = '';
		if (reason === 'unsupported-file-type') {
			msg = "Unsupported format " + detail;
		} else {
			// console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
						+ '<strong>File upload error</strong> '
						+ msg
						+ ' </div>').prependTo('#alerts');
	}
	$('#activity_desc').ace_wysiwyg({
		toolbar : [ 'font', null, 'fontSize', null, {
			name : 'bold',
			className : 'btn-info'
		}, {
			name : 'italic',
			className : 'btn-info'
		}, {
			name : 'strikethrough',
			className : 'btn-info'
		}, {
			name : 'underline',
			className : 'btn-info'
		}, null, {
			name : 'insertunorderedlist',
			className : 'btn-success'
		}, {
			name : 'insertorderedlist',
			className : 'btn-success'
		}, {
			name : 'outdent',
			className : 'btn-purple'
		}, {
			name : 'indent',
			className : 'btn-purple'
		}, null, {
			name : 'justifyleft',
			className : 'btn-primary'
		}, {
			name : 'justifycenter',
			className : 'btn-primary'
		}, {
			name : 'justifyright',
			className : 'btn-primary'
		}, {
			name : 'justifyfull',
			className : 'btn-inverse'
		}, null, {
			name : 'createLink',
			className : 'btn-pink'
		}, {
			name : 'unlink',
			className : 'btn-pink'
		}, null, {
			name : 'insertImage',
			className : 'btn-success'
		}, null, 'foreColor', null, {
			name : 'undo',
			className : 'btn-grey'
		}, {
			name : 'redo',
			className : 'btn-grey'
		} ],
		'wysiwyg' : {
			fileUploadError : showErrorAlert
		}
	}).prev().addClass('wysiwyg-style2');
	$(window).on('resize.editor', function() {
		var offset = $('#activity_desc').parent().offset();
		var winHeight = $(this).height();

		$('#activity_desc').css({
			'height' : winHeight - offset - 10,
			'max-height' : 'none'
		});
	}).triggerHandler('resize.editor');
	
	$('[data-toggle="buttons"] .btn').on(
			'click',
			function(e) {
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				var toolbar = $('#editor1').prev().get(0);
				if (which >= 1 && which <= 4) {
					toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g, '');
					if (which == 1)
						$(toolbar).addClass('wysiwyg-style1');
					else if (which == 2)
						$(toolbar).addClass('wysiwyg-style2');
					if (which == 4) {
						$(toolbar).find('.btn-group > .btn').addClass(
								'btn-white btn-round');
					} else
						$(toolbar).find('.btn-group > .btn-white').removeClass(
								'btn-white btn-round');
				}
			});
	var enableImageResize = function() {
		$('.wysiwyg-editor').on('mousedown', function(e) {
			var target = $(e.target);
			if (e.target instanceof HTMLImageElement) {
				if (!target.data('resizable')) {
					target.resizable({
						aspectRatio : e.target.width / e.target.height,
					});
					target.data('resizable', true);

					if (lastResizableImg != null) {
						// disable previous resizable image
						lastResizableImg.resizable("destroy");
						lastResizableImg.removeData('resizable');
					}
					lastResizableImg = target;
				}
			}
		}).on('click',function(e) {
					if (lastResizableImg != null
							&& !(e.target instanceof HTMLImageElement)) {
						destroyResizable();
					}
				}).on('keydown', function() {
			destroyResizable();
		});
	}
	$('#start_time').datetimepicker({
		format:'YYYY-MM-DD HH:mm:00'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#end_time').datetimepicker({
		format:'YYYY-MM-DD HH:mm:00'
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
});
function addActivity(){
	name = $('#activity_name').val();
	category = $('#activity_category').val();
	rate = $('#activity_rate').val();
	start = $('#start_time').val();
	end = $('#end_time').val();
	desc = $('#activity_desc').html();
	data ={
			name:name,
			category:category,
			rate:rate,
			start:start,
			end:end,
			desc:desc
	};
	console.log(data);
	$.post(base+"manage/activity/add",data,
			function(data){
		        if(data.success){
		        	showMessage("活动发布成功！",function(){
		        		location.href=base+"manage/activity.html";
		        	});
		        }else{
		        	showMessage("活动发布失败！");
		        }
    });
}