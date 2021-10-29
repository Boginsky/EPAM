<form action="./controller" name="command" method="post">
    <div class="modal fade" id="addAdmin" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.addInfo"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-body">
                    <input type="email" class="form-control" name="email"
                           placeholder="<fmt:message key="label.enterYourEmail" bundle="${var}"/>"required>
                </div>
                <div class="modal-body">
                    <input type="password" class="form-control" name="password"
                           placeholder="<fmt:message key="label.enterYourPassword" bundle="${var}"/>"
                           required>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="firstName"
                           placeholder="<fmt:message key="label.enterFirstName" bundle="${var}"/>"
                           required>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="lastName"
                           placeholder="<fmt:message key="label.enterLastName" bundle="${var}"/>"
                           required>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-lg btn-block" data-dismiss="modal"><fmt:message key="label.close" bundle="${var}"/></button>
                    <input type="hidden" name="userRole" />
                    <input type="hidden" name="command" value="register"/>
                    <button type="submit" class="btn btn-dark btn-lg btn-block"><fmt:message key="label.signUp" bundle="${var}"/></button>
                </div>
            </div>
        </div>
    </div>
</form>

