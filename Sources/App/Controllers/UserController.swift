//
//  File.swift
//  
//
//  Created by 李伟 on 2024/10/27.
//

import Fluent
import Vapor

final class UserController {

    func register(req: Request) throws -> EventLoopFuture<User> {

        try UserRegistrationRequest.validate(content: req)

        let registerUser = try req.content.decode(UserRegistrationRequest.self)

        let user = User(
            username: registerUser.username,
            email: registerUser.email,
            passwordHash: registerUser.password
        )

        return user.save(on: req.db).map{ @Sendable in user }

    }

    func login(req: Request) throws -> EventLoopFuture<ResultResponse<String>>{

        try UserRegistrationRequest.validate(content: req)

        let LoginUser = try req.content.decode(UserRegistrationRequest.self)

        

    }

    // Create a User
    func create(req: Request) throws -> EventLoopFuture<User> {
        let user = try req.content.decode(User.self)
        return user.save(on: req.db).map { @Sendable in user }
    }

    // Read All Users
    // 获取所有用户
    func getAll(req: Request) throws -> EventLoopFuture<ResultResponse<[User]>> {
        return User.query(on: req.db).all().map { users in
            // 将结果封装在 ResultResponse 中
            return ResultResponse(resultEnum: .success, data: users)
        }.flatMapError { error in
            // 处理错误并返回适当的响应
            let response = ResultResponse<[User]>(resultEnum: .error)
            return req.eventLoop.makeSucceededFuture(response)
        }
    }

    // Read a Specific User by ID
    func get(req: Request) throws -> EventLoopFuture<User> {
        User.find(req.parameters.get("userID"), on: req.db)
            .unwrap(or: Abort(.notFound))
    }

    // Update a User
    func update(req: Request) throws -> EventLoopFuture<User> {
        let updatedUser = try req.content.decode(User.self)
        return User.find(req.parameters.get("userID"), on: req.db)
            .unwrap(or: Abort(.notFound))
            .flatMap { user in
                user.username = updatedUser.username
                user.email = updatedUser.email
                user.passwordHash = updatedUser.passwordHash
                return user.update(on: req.db).map { user }
            }
    }

    // Delete a User
    func delete(req: Request) throws -> EventLoopFuture<HTTPStatus> {
        User.find(req.parameters.get("userID"), on: req.db)
            .unwrap(or: Abort(.notFound))
            .flatMap { $0.delete(on: req.db) }
            .transform(to: .noContent)
    }
}
