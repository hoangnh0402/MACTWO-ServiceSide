package com.mactwo.mactwocommandservice.application.command.command.category;

import lombok.Getter;

@Getter
public class DeleteCategoryCommand {
    private final Long categoryId;
    public DeleteCategoryCommand(Long categoryId) { this.categoryId = categoryId; }
}
