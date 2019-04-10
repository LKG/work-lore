<script src="${appHost}/modules/seajs/2.3.0/sea.js" data-main="application/application"  id="seajsnode" ></script>
<script   type="text/javascript" id="seajsconfig" >
  	seajs.config({
  	  charset: 'utf-8',
	  base: '${appHost}/',
	 // 设置路径，方便跨目录调用
	  paths: {
		'store': '${appHost}/modules/store',
		'editor.md': '${appHost}/modules/editor.md',
		'select2': '${appHost}/modules/select2',
		'galleria': '${appHost}/modules/galleria',
		'swiper': '${appHost}/modules/swiper',
		'printThis': '${appHost}/modules/printThis',
	  },
	  alias: {
		'$': '${appHost}/js/common/jquery.js',
		'jquery': '${appHost}/js/common/jquery.js',
	    'store': 'store/1.3.7/store.js',
	    'editor.md': 'editor.md/editormd.min.js',
	    'link-dialog': 'editor.md/plugins/link-dialog/link-dialog.js',
	    'reference-link-dialog': 'editor.md/plugins/reference-link-dialog/reference-link-dialog.js',
	    'image-dialog': 'editor.md/plugins/image-dialog/image-dialog.js',
	    'code-block-dialog': 'editor.md/plugins/code-block-dialog/code-block-dialog.js',
	    'table-dialog': 'editor.md/plugins/table-dialog/table-dialog.js',
	    'emoji-dialog': 'editor.md/plugins/emoji-dialog/emoji-dialog.js',
	    'goto-line-dialog': 'editor.md/plugins/goto-line-dialog/goto-line-dialog.js',
	    'help-dialog': 'editor.md/plugins/help-dialog/help-dialog.js',
		'html-entities-dialog': 'editor.md/plugins/html-entities-dialog/html-entities-dialog.js',
		'preformatted-text-dialog': 'editor.md/plugins/preformatted-text-dialog/preformatted-text-dialog.js',
	    'arttemplate-native': '${appHost}/js/common/template-native.js',
		'arttemplate': '${appHost}/js/common/template.js',
	    'laypage': '${appHost}/js/common/laypage.js',
		'lazyload': '${appHost}/js/common/lazyload.js',
	    'validate': '${appHost}/js/common/validate.js',
	    'artDialog': '${appHost}/js/common/dialog.js',
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
