package kg.attractor.edufood.service;

import kg.attractor.edufood.security.MyUserDetails;

public interface AuthService {
    MyUserDetails getAuthUserDetails();
}
