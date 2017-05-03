package pl.rspective.voucherify.client.module;

import pl.rspective.voucherify.client.api.VoucherifyApi;
import pl.rspective.voucherify.client.callback.VoucherifyCallback;
import pl.rspective.voucherify.client.model.redemption.RedeemVoucher;
import pl.rspective.voucherify.client.model.redemption.RedemptionsFilter;
import pl.rspective.voucherify.client.model.redemption.RollbackRedemption;
import pl.rspective.voucherify.client.model.redemption.response.RedeemVoucherResponse;
import pl.rspective.voucherify.client.model.redemption.response.RedemptionEntryResponse;
import pl.rspective.voucherify.client.model.redemption.response.RedemptionsResponse;
import pl.rspective.voucherify.client.model.redemption.response.RollbackRedemptionResponse;
import pl.rspective.voucherify.client.model.redemption.response.VoucherRedemptionsResponse;
import pl.rspective.voucherify.client.module.RedemptionsModule.ExtAsync;
import pl.rspective.voucherify.client.module.RedemptionsModule.ExtRxJava;
import pl.rspective.voucherify.client.utils.RxUtils;
import rx.Observable;

import java.util.concurrent.Executor;

public final class RedemptionsModule extends AbsModule<ExtAsync, ExtRxJava> {

  public RedemptionsModule(VoucherifyApi api, Executor executor) {
    super(api, executor);
  }

  public RedeemVoucherResponse redeem(String code, RedeemVoucher redeemVoucher) {
    return api.redeem(code, redeemVoucher);
  }

  public RedemptionEntryResponse get(String redemptionId) {
    return api.getRedemption(redemptionId);
  }

  public RedemptionsResponse list(RedemptionsFilter redemptionsFilter) {
    return api.listRedemptions(redemptionsFilter.asMap());
  }

  public VoucherRedemptionsResponse getForVoucher(String code) {
    return api.getVoucherRedemptions(code);
  }

  public RollbackRedemptionResponse rollback(String redemptionId, String reason, RollbackRedemption rollbackRedemption) {
    return api.rollbackRedemption(redemptionId, reason, rollbackRedemption);
  }

  @Override
  ExtAsync createAsyncExtension() {
    return new ExtAsync();
  }

  @Override
  ExtRxJava createRxJavaExtension() {
    return new ExtRxJava();
  }

  @Override
  public ExtAsync async() {
    return extAsync;
  }

  @Override
  public ExtRxJava rx() {
    return extRxJava;
  }

  public class ExtAsync extends AbsModule.Async {

    public void redeem(String code, RedeemVoucher redeemVoucher, VoucherifyCallback<RedeemVoucherResponse> callback) {
      RxUtils.subscribe(executor, rx().redeem(code, redeemVoucher), callback);
    }

    public void list(RedemptionsFilter redemptionsFilter, VoucherifyCallback<RedemptionsResponse> callback) {
      RxUtils.subscribe(executor, rx().list(redemptionsFilter), callback);
    }

    public void get(String redemptionId, VoucherifyCallback<RedemptionEntryResponse> callback) {
      RxUtils.subscribe(executor, rx().get(redemptionId), callback);
    }

    public void getForVoucher(String code, VoucherifyCallback<VoucherRedemptionsResponse> callback) {
      RxUtils.subscribe(executor, rx().getForVoucher(code), callback);
    }

    public void rollback(String redemptionId, String reason, RollbackRedemption rollbackRedemption, VoucherifyCallback<RollbackRedemptionResponse> callback) {
      RxUtils.subscribe(executor, rx().rollback(redemptionId, reason, rollbackRedemption), callback);
    }
  }

  public class ExtRxJava extends AbsModule.Rx {

    public Observable<RedeemVoucherResponse> redeem(final String code, final RedeemVoucher redeemVoucher) {
      return RxUtils.defer(new RxUtils.DefFunc<RedeemVoucherResponse>() {
        @Override
        public RedeemVoucherResponse method() {
          return RedemptionsModule.this.redeem(code, redeemVoucher);
        }
      });
    }

    public Observable<RedemptionEntryResponse> get(final String redemptionId) {
      return RxUtils.defer(new RxUtils.DefFunc<RedemptionEntryResponse>() {
        @Override
        public RedemptionEntryResponse method() {
          return RedemptionsModule.this.get(redemptionId);
        }
      });
    }

    public Observable<RedemptionsResponse> list(final RedemptionsFilter redemptionsFilter) {
      return RxUtils.defer(new RxUtils.DefFunc<RedemptionsResponse>() {
        @Override
        public RedemptionsResponse method() {
          return RedemptionsModule.this.list(redemptionsFilter);
        }
      });
    }

    public Observable<VoucherRedemptionsResponse> getForVoucher(final String code) {
      return RxUtils.defer(new RxUtils.DefFunc<VoucherRedemptionsResponse>() {
        @Override
        public VoucherRedemptionsResponse method() {
          return RedemptionsModule.this.getForVoucher(code);
        }
      });
    }

    public Observable<RollbackRedemptionResponse> rollback(final String redemptionId, final String reason, final RollbackRedemption rollbackRedemption) {
      return RxUtils.defer(new RxUtils.DefFunc<RollbackRedemptionResponse>() {
        @Override
        public RollbackRedemptionResponse method() {
          return RedemptionsModule.this.rollback(redemptionId, reason, rollbackRedemption);
        }
      });
    }
  }
}