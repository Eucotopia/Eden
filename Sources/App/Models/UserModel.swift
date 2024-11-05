//
//  File.swift
//  
//
//  Created by 李伟 on 2024/10/9.
//

import Fluent
import Vapor

final class User: Model, Content {
    // Table name
    static let schema = "users"

    // Primary key (ID)
    @ID(key: .id)
    var id: UUID?

    // Username field
    @Field(key: "username")
    var username: String

    // Email field
    @Field(key: "email")
    var email: String

    // Password hash (hashed in controller or service layer)
    @Field(key: "password_hash")
    var passwordHash: String

    // User bio
    @Field(key: "bio")
    var bio: String?

    // Profile image URL as String
    @Field(key: "profile_image")
    var profileImage: String?

    // Status field using enum
    @Field(key: "status")
    var status: UserStatusEnum?

    // Role field for user roles
    @Field(key: "role")
    var role: RoleEnum?

    // Created timestamp
    @Timestamp(key: "created_at", on: .create)
    var createdAt: Date?

    // Updated timestamp
    @Timestamp(key: "updated_at", on: .update)
    var updatedAt: Date?

    // Empty initializer for Fluent
    init() {}

    // Custom initializer for creating new users
    init(
        id: UUID? = nil,
        username: String,
        email: String,
        passwordHash: String,
        bio: String = "",
        profileImage: String = "",
        status: UserStatusEnum = .ACTIVE,
        role: RoleEnum = .USER
    ) {
        self.id = id
        self.username = username
        self.email = email
        self.passwordHash = passwordHash
        self.bio = bio
        self.profileImage = profileImage
        self.status = status
        self.role = role
    }
}

// Extend User to make it Authenticatable
extension User: ModelAuthenticatable {
    static let usernameKey = \User.$email
    static let passwordHashKey = \User.$passwordHash

    // Password verification logic
    func verify(password: String) throws -> Bool {
        // Using bcrypt to verify password hash
        try Bcrypt.verify(password, created: self.passwordHash)
    }
}
