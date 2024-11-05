//
//  File.swift
//  
//
//  Created by 李伟 on 2024/10/10.
//

import Fluent

struct UserModelMigration: Migration {
    func prepare(on database: any FluentKit.Database) -> NIOCore.EventLoopFuture<Void> {
        database.schema("users")
            .id()
            .field("username", .string, .required)
            .field("email", .string, .required)
            .field("password_hash", .string, .required)
            .field("bio", .string, .sql(.default("")))  // 设置 bio 默认值为空字符串
            .field("profile_image", .string, .sql(.default("")))  // 设置 profile_image 默认值为空字符串
            .field("status", .string, .required, .sql(.default("ACTIVE"))) // 设置默认状态
            .field("role", .string, .required, .sql(.default("USER"))) // 设置默认角色
            .field("created_at", .datetime)
            .field("updated_at", .datetime)
            .unique(on: "email")  // 确保 email 的唯一性
            .create()
    }

    func revert(on database: any FluentKit.Database) -> NIOCore.EventLoopFuture<Void> {
        database.schema("users").delete()
    }
}
