<script src="${appHost}/modules/seajs/sea.js" data-main="application/application"  id="seajsnode" ></script>
<script   type="text/javascript" id="seajsconfig" >
  	seajs.config({
  	  charset: 'utf-8',
	  base: '${appHost}/',
	 // 设置路径，方便跨目录调用
	  paths: {
	    'jquery': '${appHost}/modules/jquery',
		'store': '${appHost}/modules/store',
		'select2': '${appHost}/modules/select2',
		'printThis': '${appHost}/modules/printThis',
		'galleria': '${appHost}/modules/galleria',
		'swiper': '${appHost}/modules/swiper',
	  },
	  alias: {
        '$': 'https://code.jquery.com/jquery-3.4.1.min.js',
        'jquery': 'https://code.jquery.com/jquery-3.4.1.min.js',
	    'store': 'store/1.3.7/store.js',
	    'arttemplate-native': '${appHost}/js/common/template-native.js',
		'arttemplate': '${appHost}/js/common/template.js',
	    'laypage': '${appHost}/js/common/laypage.js',
		'lazyload': '${appHost}/js/common/lazyload.js',
	    'validate': '${appHost}/js/common/validate.js',
	    'art-dialog': '${appHost}/modules/artDialog/dist/dialog-plus.js',
	    'fine-uploader': '${appHost}/js/common/fine-uploader.js',
	    'lrz': '${appHost}/modules/localResizeIMG/dist/lrz.all.bundle.js',
	    'select2': 'select2/4.0.3/js/select2.full.min.js',
		'printThis': 'printThis/printThis.js',
        'pin': '${appHost}/js/common/jquery.pin.js',
	    'galleria': 'galleria/dist/galleria.js',
	    'swiper': 'swiper/js/swiper.jquery.umd.min.js',
	  },

	 });
</script>
