package org.example.securityjvt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserRegistrationModel {
        @NotNull(message = "Не должно быть пустым")
        private  String fullname;
        @NotNull(message = "Это поле не должно быть пустым")
        private  String username;
        @NotNull(message = "пароль не указан")
        private  String password;


        @Override
        public String toString() {
            return "UserRegistrationDto{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", fullname='" + fullname + '\'' ;
        }
    }

