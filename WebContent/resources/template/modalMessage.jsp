<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	function modalInfo(body, footer) {
		setTimeout(function (){ 
			$('#modal-info-body').html(body);
			$('#modal-info-footer').html(footer);
			$('#modal-info').modal();
		}, 500)
	}
	function remomeModalBackDrop(modalID) {
		$('#'+modalID).modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	}
</script>	
<jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
<div class="modal fade" tabindex="-1" role="dialog" id="modal-info">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-info-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span><h4 id="modal-title">Information</h4>
      </div>
      <div  id="modal-info-body"> 
      </div>
      <div id="modal-info-footer">
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->