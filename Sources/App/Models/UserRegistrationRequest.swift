//
//  File.swift
//  
//
//  Created by 李伟 on 2024/10/21.
//

import Fluent
import Vapor

struct UserRegistrationRequest: Content {
    let username: String
    let email: String
    let password: String
}

extension UserRegistrationRequest: Validatable {
    static func validations(_ validations: inout Validations) {
        // Validations go here.
        validations.add("email", as: String.self, is: .email)
    }
}
