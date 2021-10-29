<button type="button" style="float: right" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#changePhoto">
    <fmt:message key="label.change" bundle="${var}"/>
</button>

<form class="md-form" action="./controller" name="command" method="post" enctype="multipart/form-data">
    <div class="modal fade" id="changePhoto" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.changePicture"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-body">
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="file" name="file" placeholder="Upload file">
                        <br/>
                        <input type="hidden" name="command" value="change_photo"/>
                        <input type="hidden" name="target" value="user/">
                        <input type="hidden" name="targetId" value="${user.id}">
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success btn-lg btn-block"><fmt:message
                                    key="label.saveChanges"
                                    bundle="${var}"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
