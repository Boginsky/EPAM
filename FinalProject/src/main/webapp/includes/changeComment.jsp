<form action="./controller" name="command" method="post">
    <div class="modal fade" id="changeComment${vs.index}" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.changeComment"
                                                                                    bundle="${var}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="updatedComment"
                           placeholder="${comment.commentMessage}"
                           required>
                </div>
                <div class="modal-footer">

                    <button type="submit" class="btn btn-default btn-lg btn-block" data-dismiss="modal"><fmt:message
                            key="label.close"
                            bundle="${var}"/></button>

                    <input type="hidden" name="command" value="change_comment"/>
                    <input type="hidden" name="commentId" value="${comment.id}"/>
<%--                    <input type="hidden" name="commentAlbumId" value="${comment.albumId}"/>--%>
                    <button type="submit" class="btn btn-dark btn-lg btn-block"><fmt:message key="label.saveChanges"
                                                                                             bundle="${var}"/></button>
                </div>
            </div>
        </div>
    </div>
</form>

