(function(jQuery) {

	$.fn.BreakingNews = function(settings) {
		var defaults = {
			background : '#FFF',
			title : '红狼金股',
			titlecolor : '#FFF',
			titlebgcolor : '#BB0000',
			linkcolor : '#BB0000',
			linkhovercolor : '#BB0000',
			fonttextsize : 16,
			isbold : false,
			border : 'none',
			width : '100%',
			autoplay : true,
			timer : 3000,
			modulid : 'brekingnews',
			effect : 'fade' // or slide
		};
		var settings = $.extend(defaults, settings);

		return this.each(function() {
			settings.modulid = "#" + $(this).attr("id");
			var timername = settings.modulid;
			var activenewsid = 1;

			if (settings.isbold == true)
				fontw = 'bold';
			else
				fontw = 'normal';

			if (settings.effect == 'slide')
				$(settings.modulid + ' ul li').css({
					'display' : 'block'
				});
			else
				$(settings.modulid + ' ul li').css({
					'display' : 'none'
				});

			$(settings.modulid + ' .bn-title').html(settings.title);
			$(settings.modulid).css({
				'width' : settings.width,
				'background' : settings.background,
				'border' : settings.border,
				'font-size' : settings.fonttextsize
			});
			$(settings.modulid + ' ul').css({
				'left' : $(settings.modulid + ' .bn-title').width() + 40
			});
			$(settings.modulid + ' .bn-title').css({
				'background' : settings.titlebgcolor,
				'color' : settings.titlecolor,
				'font-weight' : fontw
			});
			$(settings.modulid + ' ul li a').css({
				'color' : settings.linkcolor,
				'font-weight' : fontw,
				'height' : parseInt(settings.fonttextsize) + 6
			});
			$(settings.modulid + ' ul li').eq(parseInt(activenewsid - 1)).css({
				'display' : 'block'
			});

			// Links hover events ......
			$(settings.modulid + ' ul li a').hover(function() {
				$(this).css({
					'color' : settings.linkhovercolor
				});
			}, function() {
				$(this).css({
					'color' : settings.linkcolor
				});
			});

			// Arrows Click Events ......
			$(settings.modulid + ' .bn-arrows span').click(function(e) {
				if ($(this).attr('class') == "bn-arrows-left")
					BnAutoPlay('prev');
				else
					BnAutoPlay('next');
			});

			// Timer events ...............
			if (settings.autoplay == true) {
				timername = setInterval(function() {
					BnAutoPlay('next')
				}, settings.timer);
				$(settings.modulid).hover(function() {
					clearInterval(timername);
				}, function() {
					timername = setInterval(function() {
						BnAutoPlay('next')
					}, settings.timer);
				});
			} else {
				clearInterval(timername);
			}

			// timer and click events function ...........
			function BnAutoPlay(pos) {
				if (pos == "next") {
					if ($(settings.modulid + ' ul li').length > activenewsid)
						activenewsid++;
					else
						activenewsid = 1;
				} else {
					if (activenewsid - 2 == -1)
						activenewsid = $(settings.modulid + ' ul li').length;
					else
						activenewsid = activenewsid - 1;
				}

				if (settings.effect == 'fade') {
					$(settings.modulid + ' ul li').css({
						'display' : 'none'
					});
					$(settings.modulid + ' ul li').eq(
							parseInt(activenewsid - 1)).fadeIn();
				} else {
					$(settings.modulid + ' ul').animate(
							{
								'marginTop' : -($(settings.modulid + ' ul li')
										.height() + 20)
										* (activenewsid - 1)
							});
				}
			}

			// links size calgulating function ...........
			$(window).resize(
					function(e) {
						if ($(settings.modulid).width() < 360) {
							$(settings.modulid + ' .bn-title').html('&nbsp;');
							$(settings.modulid + ' .bn-title').css({
								'width' : '4px',
								'padding' : '10px 0px'
							});
							$(settings.modulid + ' ul').css({
								'left' : 4
							});
						} else {
							$(settings.modulid + ' .bn-title').html(
									settings.title);
							$(settings.modulid + ' .bn-title').css({
								'width' : 'auto',
								'padding' : '10px 20px'
							});
							$(settings.modulid + ' ul')
									.css(
											{
												'left' : $(
														settings.modulid
																+ ' .bn-title')
														.width() + 40
											});
						}
					});
		});

	};
	function showMessage(message,callbackFn){
		bootbox.dialog({
			message: '<b>'+message+'</b>',
			buttons: {
				"success" : {
					"label" : "OK",
					"className" : "btn-sm btn-danger",
					callback: callbackFn
				}
			}
		});
	}
})(jQuery);