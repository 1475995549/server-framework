package cn.zhihan.framework.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@ApiModel(value = "PageVo", description = "分页vo")
public class PageVo<T extends OneVo> implements Serializable {
    
    @ApiModelProperty(value = "page")
    private Integer page;
    @ApiModelProperty(value = "size")
    private Integer size;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "总数")
    private Integer totalSize;
    
    @ApiModelProperty(value = "列表")
    private List<T> array;
    
    public PageVo(int page, int size, int totalSize, List<T> array) {
        this.page = page;
        this.size = size;
        this.totalPage = size == 0 ? 0 : ((totalSize - 1) / size + 1);
        this.totalSize = totalSize;
        this.array = array;
        this.sequence();
    }
    
    public PageVo(List<T> array) {
        this(1, array.size(), 1, array.size(), array);
    }
    
    public PageVo(PageInfo<T> pageInfo) {
        //only page信息 不包含list
        this(pageInfo.getPageNum(), pageInfo.getPageSize(), (int) pageInfo.getTotal(), null);
    }
    
    public void list(List<T> list) {
        //list单独设置
        this.array = list;
        this.sequence();
    }
    
    private void sequence() {
        if (this.array == null) {
            return;
        }
        for (int i = 0; i < this.array.size(); i++) {
            this.array.get(i).setSequence((this.page - 1) * this.size + 1 + i);
        }
    }
    
}
