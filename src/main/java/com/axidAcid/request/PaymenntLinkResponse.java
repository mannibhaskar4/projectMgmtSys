package com.axidAcid.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymenntLinkResponse {

    private String payment_linkURL;
    private String payment_LinkId;

}
