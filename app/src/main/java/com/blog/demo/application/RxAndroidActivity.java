package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * Created by cn on 2018/4/28.
 */

public class RxAndroidActivity extends Activity implements View.OnClickListener {
    private static final String LOGTAG = "RxAndroidActivity";

    private Subscriber<String> mSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            LogUtil.log(LOGTAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.log(LOGTAG, "onError");
        }

        @Override
        public void onNext(String s) {
            LogUtil.log(LOGTAG, "onNext: " + s);
        }

        @Override
        public void onStart() {
            super.onStart();
            LogUtil.log(LOGTAG, "onStart");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_rx_android);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            Observable.create(new Observable.OnSubscribe<String>(){
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext("next1");
                    subscriber.onNext("next2");
                    subscriber.onCompleted();
                }
            }).subscribe(mSubscriber);
        } else if (v.getId() == R.id.btn2) {
//            Observable.interval(3, TimeUnit.SECONDS)
//                    .subscribe(new Action1<Long>() {
//                        @Override
//                        public void call(Long aLong) {
//                            Log.d("RxAndroidActivity", "interval:"+ aLong.intValue());
//                        }
//                    });
            Observable.range(0, 5)
                    .repeat(2)
                    .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                            Log.d(LOGTAG, "range:"+ integer.intValue());
                        }
                    });
        } else if (v.getId() == R.id.btn3) {
            Observable.just("itach85").map(new Func1<String, String>() {
                @Override
                public String call(String s) {
                    return "http://blog.csdn.net/" + s;
                }
            }).subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    LogUtil.log(LOGTAG, "map: " + s);
                }
            });
            List<String> list = new ArrayList<>();
            list.add("itach85");
            list.add("itach86");
            list.add("itach87");
            list.add("itach88");
            Observable.from(list).flatMap(new Func1<String, Observable<String>>() {
                @Override
                public Observable<String> call(String s) {
                    return Observable.just("http://blog.csdn.net/" + s);
                }
            }).subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    LogUtil.log(LOGTAG, "flatmap: " + s);
                }
            });

            Observable.from(list)
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            return "http://blog.csdn.net/" + s;
                        }
                    }).buffer(3)
                    .subscribe(new Action1<List<String>>() {
                        @Override
                        public void call(List<String> strings) {
                            for (String s : strings) {
                                LogUtil.log(LOGTAG, "buffer: " + s);
                            }
                            LogUtil.log(LOGTAG, "------------------------------");
                        }
                    });
        } else if (v.getId() == R.id.btn4) {
            Swordsman s1 = new Swordsman("韦一笑", "A");
            Swordsman s2 = new Swordsman("张三丰", "SS");
            Swordsman s3 = new Swordsman("周芷若", "S");
            Swordsman s4 = new Swordsman("宋远桥", "S");
            Swordsman s5 = new Swordsman("殷梨亭", "A");
            Swordsman s6 = new Swordsman("张无忌", "SS");
            Swordsman s7 = new Swordsman("鹤笔翁", "S");
            Swordsman s8 = new Swordsman("宋青书", "A");

            Observable.concat(Observable.just(s1, s2, s3, s4, s5, s6, s7, s8)
                    .groupBy(new Func1<Swordsman, String>() {
                        @Override
                        public String call(Swordsman swordsman) {
                            return swordsman.getLevel();
                        }
                    })).subscribe(new Action1<Swordsman>() {
                        @Override
                        public void call(Swordsman swordsman) {
                            LogUtil.log(LOGTAG, "groupby: " + swordsman.getName() + "---" + swordsman.getLevel());
                        }
                    });

        } else if (v.getId() == R.id.btn5) {
            Observable.just(1, 2, 3, 4).filter(new Func1<Integer, Boolean>() {
                @Override
                public Boolean call(Integer integer) {
                    return integer > 2;
                }
            }).subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    LogUtil.log(LOGTAG, "filter: " + integer);
                }
            });

            Observable.just(1, 2, 3, 4).elementAt(2).subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    LogUtil.log(LOGTAG, "elementAt: " + integer);
                }
            });

            Observable.just(1, 2, 3, 4, 3, 2, 2, 1).distinct().subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    LogUtil.log(LOGTAG, "distinct: " + integer);
                }
            });
        } else if (v.getId() == R.id.btn6) {
            Observable<Integer> obs1 = Observable.just(1, 2, 3).subscribeOn(Schedulers.io());
            Observable<Integer> obs2 = Observable.just(4, 5, 6);

            obs1.startWith(1, 2)
                    .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                            LogUtil.log(LOGTAG, "startWith: " + integer);
                        }
                    });
            Observable.merge(obs1, obs2).subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    LogUtil.log(LOGTAG, "merge: " + integer);
                }
            });

            Observable.concat(obs1, obs2).subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    LogUtil.log(LOGTAG, "concat: " + integer);
                }
            });

            Observable.zip(obs1, obs2, new Func2<Integer, Integer, Integer>() {
                @Override
                public Integer call(Integer integer, Integer integer2) {
                    return integer + integer2;
                }
            }).subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    LogUtil.log(LOGTAG, "zip: " + integer);
                }
            });

        }
    }

    private static class Swordsman {
        String name;
        String level;

        Swordsman(String name, String level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }
    }
}
