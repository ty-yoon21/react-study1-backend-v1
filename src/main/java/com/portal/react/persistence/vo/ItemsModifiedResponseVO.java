package com.portal.react.persistence.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemsModifiedResponseVO {

    private int addedItemsRequestedCount;
    private int editedItemsRequestCount;
    private int deletedItemsRequestedCount;
    private int savedItemsRequestedCount;

    private int addedItmesResultCount;
    private int editedItmesResultCount;
    private int deletedItemsResultCount;
    private int savedItmesResultCount;

    @Builder
    public ItemsModifiedResponseVO(int addedItemsRequestedCount, int editedItemsRequestCount,
                                   int deletedItemsRequestedCount, int addedItmesResultCount, int editedItmesResultCount,
                                   int deletedItemsResultCount, int savedItemsRequestedCount, int savedItmesResultCount) {
        this.addedItemsRequestedCount = addedItemsRequestedCount;
        this.editedItemsRequestCount = editedItemsRequestCount;
        this.deletedItemsRequestedCount = deletedItemsRequestedCount;
        this.addedItmesResultCount = addedItmesResultCount;
        this.editedItmesResultCount = editedItmesResultCount;
        this.deletedItemsResultCount = deletedItemsResultCount;
        this.savedItemsRequestedCount = savedItemsRequestedCount;
        this.savedItmesResultCount = savedItmesResultCount;
    }
}
