//package com.example.seniya_v2.filter;
//
//import com.example.seniya_v2.provider.JwtProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter {
//    private final JwtProvider jwtProvider;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request
//            , HttpServletResponse response
//            , FilterChain filterChain
//    ) throws ServletException, IOException{
//        try {
//            String authorizationHeader = request.getHeader("Authorization");
//
//            String token = (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
//                    ? jwtProvider.removeBearer(authorizationHeader)
//                    : null;
//
//            if (token == null || !jwtProvider.isValidToken(token)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            String username = jwtProvider.getUsenameFromJwt(token);
//            String roles = jwtProvider.getRoleFromJwt(token);
//            setAuthenticationContext(request,username,roles);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private void setAuthenticationContext(HttpServletRequest request, String username, String roles) {
//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//        AbstractAuthenticationToken authenticationToken = new UsernamePassWordAuthenticationToken(username, null, Collections.singletonList(authority));
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().builedDetails(request));
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//        securityContext.setAuthentication(authenticationToken);
//        securityContextHolder.setContext(securityContext);
//
//
//    }
//
//}
