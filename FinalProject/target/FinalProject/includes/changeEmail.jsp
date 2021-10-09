<button type="button" style="float: right" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#changeEmail">
    <fmt:message key="label.change" bundle="${var}"/>
</button>

<form action="./controller" name="command" method="post">
    <div class="modal fade" id="changeEmail" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.changeEmail"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-body">
                    <input type="email" class="form-control" name="updatedEmail" placeholder=${user.email} required/>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-lg btn-block" data-dismiss="modal"><fmt:message key="label.close"
                                                                                                                     bundle="${var}"/></button>
                    <input type="hidden" name="command" value="change_email"/>
                    <button type="submit" class="btn btn-success btn-lg btn-block"><fmt:message key="label.saveChanges"
                                                                                                bundle="${var}"/></button>
                </div>
            </div>
        </div>
    </div>
</form>