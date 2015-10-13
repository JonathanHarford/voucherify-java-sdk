package com.rspective.voucherify.client.module;

import com.rspective.voucherify.client.api.VoucherifyApi;
import com.rspective.voucherify.client.model.Voucher;
import com.rspective.voucherify.client.model.VoucherRedemption;

import java.util.concurrent.Executor;

/**
 * Vouchers Module to manage communication with Voucherify
 */
public final class VoucherModule extends BaseModule<Voucher, VoucherRedemption> {

    /**
     *
     * @param api describes Voucherify REST API
     * @param executor of threads for current platform
     */
    public VoucherModule(VoucherifyApi api, Executor executor) {
        super(api, executor);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public ExtAsync async() {
        return extAsync;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public ExtRxJava rx() {
        return extRxJava;
    }

}
