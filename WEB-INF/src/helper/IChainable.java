package helper;

import java.util.function.Consumer;

public interface IChainable<T> {

  // Consumerで引数がT型（実装クラスの型）で戻り値なしの関数を受け取る
  // defaultで定義しているので実装クラスでのオーバーライドは不要
  default T chain(Consumer<T> consumer) {
    // Chainableの実装クラスがresourceに入る
    T resource = (T) this;
    // 戻り値なしの関数を実行する
    consumer.accept(resource);
    // 実装クラスを返す
    return resource;
  }

}