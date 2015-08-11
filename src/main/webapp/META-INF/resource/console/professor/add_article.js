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
	$('#professor_addnotice_free_editor').ace_wysiwyg({
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
	$('#professor_addnotice_charge_editor').ace_wysiwyg({
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
		var offset = $('#professor_addnotice_editor').parent().offset();
		var winHeight = $(this).height();

		$('#professor_addnotice_free_editor').css({
			'height' : winHeight - offset - 10,
			'max-height' : 'none'
		});
		$('#professor_addnotice_charge_editor').css({
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
});
function changeSelect(){
	category = $('input[name="professor_notice_category"]:checked').val();
	switch(category){
		case "FREE" : 
			$('#professor_addnotice_charge_editor_div').css('display','none');
			break;
		case "CHARGE" : 
			$('#professor_addnotice_charge_editor_div').css('display','block');
			break;
		default : break;
	}
}
function addNotice(){
	title = $('#professor_notice_title').val();
	keyword = $('#professor_notice_keyword').val();
	category = $('input[name="professor_notice_category"]:checked').val();
	freeContent = $('#professor_addnotice_free_editor').html();
	chargeContent = $('#professor_addnotice_charge_editor').html();
	$.post(base+"professor/notice/add",
			{title:title,keyword:keyword,category:category,freeContent:freeContent,chargeContent:chargeContent},
			function(data){
		        if(data.success){
		        	showMessage("撰写锦囊成功！",function(){
		        		location.href=base+"professor/notice.html";
		        	});
		        }else{
		        	showMessage("撰写锦囊失败！");
		        }
    });
}