package com.hino.hearts.model

class ApprovalDocumentModel(accountName: String, documentType: String, accountBranch: String) {
    var accountName: String = accountName
    var documentType: String = documentType
    var accountBranch: String = accountBranch
    var isSelected: Boolean = false
}