package jp.gr.java_conf.saboten.wicketutils.app;


import jp.gr.java_conf.saboten.wicketutils.converter.DoubleConverter;
import jp.gr.java_conf.saboten.wicketutils.converter.FloatConverter;

import org.apache.wicket.Component;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.resource.JQueryResourceReference;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractApp extends WebApplication {

	private static final Logger logger = LoggerFactory.getLogger(AbstractApp.class);

	protected abstract void onStartRequest();

	protected abstract void onFinishRequest();

	protected abstract void onException();

	protected abstract Page newInternalErrorPage(Class<? extends IRequestablePage> targetPageClass, Exception e);

	protected abstract Page newExpiredPageClass(Class<? extends IRequestablePage> targetPageClass);

	/*
	public static final Configuration CONFIG;
	static {
		try {
			CONFIG = new PropertiesConfiguration("system.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	 */

	@Override
	protected void init() {

		super.init();


		// === IApplicationSettings ====================================
		// @see org.apache.wicket.settings.def.ApplicationSettings

		// 最大UPLOAD可能サイズ
		getApplicationSettings().setDefaultMaximumUploadSize(Bytes.megabytes(10));

// ここらへんの設定は使用しない
////		// 内部エラー発生時の遷移先ページ
//		getApplicationSettings().setInternalErrorPage(InternalErrorPage.class);
//
////		// セッション時間切れ時の遷移先ページ
//		getApplicationSettings().setPageExpiredErrorPage(PageExpiredErrorPage.class);


		// === IExceptionSettings ====================================

		// Exception発生時の挙動
		// ・SHOW_EXCEPTION_PAGE＝開発モードのデフォルト
		// ・SHOW_INTERNAL_ERROR_PAGE＝配備モードのデフォルト
		// ・SHOW_NO_EXCEPTION_PAGE
//		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_EXCEPTION_PAGE);


		// === IFrameworkSettings ===============================================
		// @see org.apache.wicket.settings.def.FrameworkSettings

//		// IEventを使ったEventSystemが、デフォルトではどうにも手数がかかるし、instanceofで分岐するところとかが素敵なので
//		// どうにかしたいが、そういう際に、独自のIEventDispatcherを追加してやるみたい←追加しても元々のOnEventメソッドは呼ばれるので、処理の分散になってしまわないか？
//		getFrameworkSettings().add(new IEventDispatcher(){
//			@Override
//			public void dispatchEvent(Object sink, IEvent<?> event, Component component) {
//
//			}
//		});


		// === IPageSettings ====================================
		// @see org.apache.wicket.settings.def.PageSettings

		// ページのバージョン管理を行う：デフォルト＝true
		getPageSettings().setVersionPagesByDefault(true);


		// === IRequestCycleSettings ====================================
		// @see org.apache.wicket.settings.def.RequestCycleSettings

		// レスポンスHTMLのエンコーディング:default="UTF-8"
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

		// リクエストタイムアウト：デフォルト＝1分
		getRequestCycleSettings().setTimeout(Duration.ONE_MINUTE);

		getRequestCycleSettings().setGatherExtendedBrowserInfo(false);



		// === IMarkupSettings ====================================
		// @see org.apache.wicket.settings.def.MarkupSettings

		// HTMLファイルの文字コード設定：デフォルトはOS依存なので、実質指定必須
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

		// 出力HTMLファイルから、先頭のXML定義を削除する＝IEはXML定義有無でレンダリングが微妙に変るとか：デフォルト＝false
		//getMarkupSettings().setStripXmlDeclarationFromOutput(true);←なくなった。挙動として、MIME＝application/xhtml+xmlにすればXML宣言が出るがそれ以外は付けてても消える（http://list-archives.org/2012/11/09/wicket-ja-user-lists-sourceforge-jp/wicket-ja-user-681-%09xml%E5%AE%A3%E8%A8%80%E3%81%8C%E5%87%BA%E5%8A%9B%E3%81%95%E3%82%8C%E3%81%AA%E3%81%84/f/7253271497）

		// HTMLファイルにXML宣言が無い場合にエラーにする：デフォルト＝false
		getMarkupSettings().setThrowExceptionOnMissingXmlDeclaration(false);

		// <wicket:link>タグで囲まなくとも、HTML内に記述されたリソースリンクが全て変換される。
		// 場合によってはやり過ぎになるかも
		getMarkupSettings().setAutomaticLinking(false);

		// デフォルトは"<em>"だけ
		getMarkupSettings().setDefaultBeforeDisabledLink("<em class='disable'>");


		// === IResourceSettings ====================================
		// @see org.apache.wicket.settings.def.ResourceSettings

		// リソースをブラウザキャッシュされないよう、更新日をURLに自動付加する：デフォルト＝false
//		getResourceSettings().setAddLastModifiedTimeToResourceReferenceUrl(true);←なくなってデフォでtrue、プログラムで制御するらしい

		// GZip圧縮を無効にする：デフォルト＝false
//		getResourceSettings().setDisableGZipCompression(true);←なくなったっぽい

		// 存在しないリソース宛てリクエストを受けた際にエラーとする：デフォルト＝true
		getResourceSettings().setThrowExceptionOnMissingResource(true);

		// こうしないと、Html上で'../folder/resource'と指定しても稼働時には404になる←かどうかは検証してない
//		getResourceSettings().setParentFolderPlaceholder("$up$");

		// クラスパス上のリソースファイルを公開する際に、公開させないファイルを拡張子等で設定する
		getResourceSettings().setPackageResourceGuard(
				new CustomizablePackageResourceGuard()
					.addBlockExtension("properties")
					.addBlockExtension("class")
					.addBlockExtension("java")
					.addBlockExtension("xml")
					.addBlockFile("applicationContext.xml")
					.addBlockFile("log4j.xml"));

		// クラスパス外のリソースのありかを、絶対パスで指定する
		// Finder自体を変更する実装もあり
//		IResourcePath pathFinder = (IResourcePath)getResourceSettings().getResourceFinder();
//		pathFinder.add("");


		// === IJavaScriptLibrarySettings ====================================
		// @see org.apache.wicket.settings.def.JavaScriptLibrarySettings

		// 使用するJQueryライブラリのバージョン（参照設定）。default = jquery-1.10.1.js ：IE6にも対応する1.x系。但しJQueryは1.9系で非推奨系を切り捨てたので、Libによってはこれでは動かないらしい。
		// @see org.apache.wicket.resource.JQueryResourceReference.VERSION_1
		getJavaScriptLibrarySettings().setJQueryReference(JQueryResourceReference.get());
		//getJavaScriptLibrarySettings().setJQueryReference(new DynamicJQueryResourceReference());


		// === IDebugSettings ====================================
		// @see org.apache.wicket.settings.def.DebugSettings

		// Ajaxデバッグwindowを表示する＝開発モードでは自動でtrueになる?
		getDebugSettings().setAjaxDebugModeEnabled(false);

		// 開発支援用
		getDebugSettings().setOutputComponentPath(true);
		getDebugSettings().setOutputMarkupContainerClassName(true);

		getDebugSettings().setComponentUseCheck(true);
		getDebugSettings().setLinePreciseReportingOnAddComponentEnabled(true);
		getDebugSettings().setLinePreciseReportingOnNewComponentEnabled(true);

		// TODO DevTools
		getDebugSettings().setDevelopmentUtilitiesEnabled(true);



		// === IRequestLoggerSettings ====================================
		// @see org.apache.wicket.settings.def.RequestLoggerSettings

		// リクエスト情報の他、メモリ使用量などを毎回出力する：デフォルト＝false
		// 大量にでます
		getRequestLoggerSettings().setRequestLoggerEnabled(true);


		// === IStoreSettings =====================================
		// @see org.apache.wicket.settings.def.StoreSettings

		// DataStoreに格納されるまでのメモリキャッシュサイズらしい
		getStoreSettings().setInmemoryCacheSize(40);

		// DefaultのIDataStoreであるDiskDataStoreが保存する上限。超えると古いのを破棄する
		getStoreSettings().setMaxSizePerSession(Bytes.megabytes(10));

		// ちなみにサーバのディレクトリ上にファイルを生成しないようにするには、DefaultPageManagerProviderを継承してこんなふうにする模様
//		setPageManagerProvider(new DefaultPageManagerProvider(this) {
//			protected IDataStore newDataStore() {
//				return new HttpSessionDataStore(
//						new DefaultPageManagerContext(),
//						new PageNumberEvictionStrategy(12)); // sessionあたり12ページまで保持
//			}
//		});





		// mount
//		mount("/html", PackageName.forClass(Top.class)); // Rootへのマッピングは不可能っぽい


//		getRequestCycleListeners().add(new PageRequestHandlerTracker());
		getRequestCycleListeners().add(new AbstractRequestCycleListener(){

			private final ThreadLocal<IRequestHandler> tHandler = new ThreadLocal<IRequestHandler>();

			@Override
			public void onBeginRequest(RequestCycle cycle) {
				AbstractApp.this.onStartRequest();
			}

			@Override
			public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler)
			{
				super.onRequestHandlerResolved(cycle, handler);
				logger.debug("@@@ onRequestHandlerResolved:" + handler.toString());
				if (tHandler.get() == null)
					tHandler.set(handler);
			}
			@Override
			public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler)
			{
				super.onRequestHandlerResolved(cycle, handler);
				logger.debug("@@@ onRequestHandlerScheduled:" + handler.toString());
				if (tHandler.get() == null)
					tHandler.set(handler);
			}

			@Override
			public IRequestHandler onException(RequestCycle cycle, Exception ex)
			{
				AbstractApp.this.onException();

				Class<? extends IRequestablePage> requestedPageClass = null;
				IRequestHandler handler = tHandler.get();
				if (handler != null) {
					Class<? extends IRequestHandler> handlerClass = handler.getClass();
					if (handlerClass == RenderPageRequestHandler.class) {
						// org.apache.wicket.core.request.handler.PageProvider.getPageInstance()の中で再度PageExpiredを投げよるんだがどうしたものか
						try {
							requestedPageClass = ((RenderPageRequestHandler)handler).getPageClass();
						} catch (PageExpiredException e) {}
					}
				}

//				AppendingStringBuffer sb = new AppendingStringBuffer(128);
//				if (handler != null) {
//					Class<? extends IRequestHandler> handlerClass = handler.getClass();
//					sb.append("handler=");
//					sb.append(handlerClass.isAnonymousClass() ? handlerClass.getName() : Classes.simpleName(handlerClass));
//					if (handlerClass == RenderPageRequestHandler.class) {
//						RenderPageRequestHandler rprh = (RenderPageRequestHandler)handler;
//						sb.append("{pageClass=");
//						sb.append(rprh.getPageClass().getName());
//						sb.append("}");
//					}
//				}

				if (ex instanceof PageExpiredException) {
					return new RenderPageRequestHandler(
							new PageProvider(newExpiredPageClass(requestedPageClass)));
				} else {
					return new RenderPageRequestHandler(
							new PageProvider(newInternalErrorPage(requestedPageClass, ex)));
				}
			}

			@Override
			public void onEndRequest(RequestCycle cycle)
			{
				// Exception発生時もここは通るので、2重フックしないよう注意
				AbstractApp.this.onFinishRequest();

				tHandler.remove();
			}
		});

		getComponentInstantiationListeners().add(new IComponentInstantiationListener() {
			public void onInstantiation(Component component) {
				component.setOutputMarkupId(true); // Ajax前提なので、一律trueでOK
				component.setOutputMarkupPlaceholderTag(true);
			}
		});

	}


	protected IConverterLocator newConverterLocator() {
		ConverterLocator c = new ConverterLocator();
		// 一部を上書き
		c.set(Double.TYPE, DoubleConverter.INSTANCE);
		c.set(Double.class, DoubleConverter.INSTANCE);
		c.set(Float.TYPE, FloatConverter.INSTANCE);
		c.set(Float.class, FloatConverter.INSTANCE);
//		c.set(java.sql.Date.class, SqlDateConverter.INSTANCE);
		return c;
	}
}
