//package by.boginsky.audiostore.controller.listener;
//
//import by.boginsky.audiostore.model.entity.user.User;
//
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//import static by.boginsky.audiostore.util.constants.Attribute.USER;
//
//@WebListener
//public class HttpSessionListenerImpl implements HttpSessionListener {
//
//    @Override
//    public void sessionCreated(HttpSessionEvent se) {
//        HttpSession session = se.getSession();
//        User user = (User) session.getAttribute(USER);
//        if (user == null) {
//            user = User.builder()
//                    .setUserRole(User.UserRole.GUEST)
//                    .build();
//            session.setAttribute(USER, user);
//        }
//    }
//}
