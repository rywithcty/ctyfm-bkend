package org.xf.iform.service.data.dto.user;

import org.xf.iform.service.data.po.user.ModulePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicInfoDto {
    String employeeId;

    String cnName;

    String adAccount;

    List<ModulePo> modules;
}
