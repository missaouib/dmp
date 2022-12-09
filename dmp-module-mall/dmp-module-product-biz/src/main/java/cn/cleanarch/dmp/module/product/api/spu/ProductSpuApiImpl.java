package cn.cleanarch.dmp.module.product.api.spu;

import cn.cleanarch.dmp.framework.common.util.collection.CollectionUtils;
import cn.cleanarch.dmp.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.cleanarch.dmp.module.product.convert.spu.ProductSpuConvert;
import cn.cleanarch.dmp.module.product.dal.dataobject.spu.ProductSpuDO;
import cn.cleanarch.dmp.module.product.dal.mysql.spu.ProductSpuMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * TODO LeeYan9: 类注释;
 *
 * @author LeeYan9
 * @since 2022-09-06
 */
@Service
@Validated
public class ProductSpuApiImpl implements ProductSpuApi {

    @Resource
    private ProductSpuMapper productSpuMapper;

    @Override
    public List<ProductSpuRespDTO> getSpuList(Collection<Long> spuIds) {
        // TODO TODO LeeYan9: AllEmpty?
        if (CollectionUtils.isAnyEmpty(spuIds)) {
            return Collections.emptyList();
        }
        List<ProductSpuDO> productSpuDOList = productSpuMapper.selectBatchIds(spuIds);
        return ProductSpuConvert.INSTANCE.convertList2(productSpuDOList);
    }
}
