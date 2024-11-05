//
//  File.swift
//  Eden
//
//  Created by 李伟 on 2024/10/27.
//

import Foundation

enum UserStatusEnum:String, Codable{
    // 用户账户正常，可以登录和使用所有权限。
    case ACTIVE
    // 用户账户已注册但未激活，比如需要通过邮件验证激活。
    case INACTIVE
    // 用户账户被封禁，可能是由于违反使用规定。禁止登录和操作。
    case BANNED
    // 用户账户被暂时停用，通常是因为活动异常或违反规则的轻度处理。
    case SUSPENDED
    // 用户选择删除账户或系统自动删除（软删除）。用户数据可能被保留，但禁止登录。
    case DELETED
}
