<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
	function processMmodalSuccess(title, footer) {
		$('#process-success-msg-body').html(title);
		$('#process-success-footer').html(footer);
		setTimeout(function (){
			$('#process-success-modal').modal();
		}, 500)
	}
	
	function processMmodalFail(title, footer) {
		$('#process-fail-msg-body').html(title);
		$('#process-fail-footer').html(footer);
		setTimeout(function (){
			$('#process-fail-modal').modal();
		}, 500)
	}
	function modalLoading(title, footer) {
		$('#process-loading-msg-body').html(title);
		$('#process-loading-footer').html(footer);
		$('#process-loading-modal').modal();
	}
	
	function remomeModalBackDrop(modalID) {
		$('#'+modalID).modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	}
</script> 
<div id="process-loading-modal" class="modal fade" role="dialog"	data-backdrop="static" data-keyboard="false" aria-hidden="true">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-info-title">
					<spring:message code="info.header" />
				</h4>
			</div>
			<div id="process-loading-body" class="modal-body">
				<img alt=""	src="${pageContext.request.contextPath}/resources/images/loader.gif" style="width: 42px; height: 42px;">
				<span id="process-loading-msg-body"></span>
			</div>
			<div id="process-loading-footer" class="modal-footer"></div>
		</div>
	</div>
</div>
<div id="process-success-modal" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-info-title">
					<spring:message code="info.header" />
				</h4>
			</div>
			<div id="process-success-body" class="modal-body">
				<img alt=""	src="${pageContext.request.contextPath}/resources/images/success-icon.png" style="width: 44px; height: 42px;">
				<span id="process-success-msg-body"></span>
			</div>
			<div id="process-success-footer" class="modal-footer"></div>
		</div>
	</div>
</div>

<div id="process-fail-modal" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-info-title">
					<spring:message code="info.header" />
				</h4>
			</div>
			<div id="process-success-body" class="modal-body">
				<img alt=""	src="${pageContext.request.contextPath}/resources/images/error.png" style="width: 44px; height: 42px;">
				<span id="process-fail-msg-body"></span>
			</div>
			<div id="process-fail-footer" class="modal-footer"></div>
		</div>
	</div>
</div>