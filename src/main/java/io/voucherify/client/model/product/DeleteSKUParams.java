package io.voucherify.client.model.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import io.voucherify.client.utils.AbstractParams;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class DeleteSKUParams extends AbstractParams<String, Object> {

  private Boolean force;

  @Override
  public Map<String, Object> asMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("force", force);
    return map;
  }
}
