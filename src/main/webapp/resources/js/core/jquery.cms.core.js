/* 自定义插件 */
(function($){
	
	//ztree简单封装的插件
	$.fn.mytree = function(opts){
		var setting = $.extend({
			data:{
				simpleData:{
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: -1
				}
			},
			view: {
				dblClickExpand: false,
				selectedMulti: false
			},
			async: {
				enable: true,
				url: opts?(opts.url||"treeAll"):"treeAll"
				
			},
			mine: {
				listChild:1,
				srcElement:"#cc"
			},
			callback:{
				onAsyncSuccess:function(){
					if(opts.mine.expandAll)
						t.expandAll(true);
				}
			}
			
		},opts||{});
		
		var _mine = setting.mine;
		var t = $.fn.zTree.init($(this), setting);
		if(_mine.listChild) {
			t.setting.callback.onClick = listChild;
		}
		
		function listChild(event,treeId,treeNode) {
			$(_mine.srcElement).attr("src","channels/"+treeNode.id);
		}
	}
	
	// 左侧折叠菜单的插件
	$.fn.myaccordion = function(opts) {
		var settings = $.extend({
			selectedClz:"navSelected",
			titleTagName:"h3"
		},opts||{});
		var titleNode = $(this).find("ul>"+settings.titleTagName);
		var selectedNode = $(this).find("ul."+settings.selectedClz+">"+settings.titleTagName);
		titleNode.css("cursor","pointer");
		titleNode.nextAll().css("display","none");
		selectedNode.nextAll().css("display","block");
		titleNode.click(function(){
			var checked = $(this).parent().hasClass(settings.selectedClz);
			if(checked) {
				$(this).parent().removeClass(settings.selectedClz);
				$(this).nextAll().slideUp();
			} else {
				$(this).parent().addClass(settings.selectedClz);
				$(this).nextAll().slideDown();
			}
		});
	};
	
	$.fn.trColorChange = function(opts) {
		var settings = $.extend({
			overClz:"trMouseover",
			evenClz:"trEvenColor"
		},opts||{});
		$(this).find("tbody tr:even").addClass(settings.evenClz);
		$(this).find("tbody tr").on("mouseenter mouseleave",function(){
			$(this).toggleClass(settings.overClz);
		});
	};
	
	// alert的插件
	$.fn.confirmOperator = function(opts) {
		var settings = $.extend({
			msg:"该操作不可逆，确定进行该操作吗？",
			eventName:"click"
		},opts||{});
		$(this).on(settings.eventName,function(event){
			if(!confirm(settings.msg)) {
				event.preventDefault();
			}
		});
	}
})(jQuery)