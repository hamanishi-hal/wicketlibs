package jp.gr.java_conf.saboten.wicketutils.app;

import java.lang.ref.WeakReference;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

/**
 * 基底クラスを変更しただけ。実装は丸パクリ
 *
 * @see org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
 *
 */
public abstract class AbstractAuthApp extends AbstractApp
implements IRoleCheckingStrategy, IUnauthorizedComponentInstantiationListener {

	private final WeakReference<Class<? extends AbstractAuthenticatedWebSession>> webSessionClassRef;

	protected abstract Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass();

	protected abstract Class<? extends WebPage> getSignInPageClass();



	public AbstractAuthApp() {
		this.webSessionClassRef = new WeakReference<Class<? extends AbstractAuthenticatedWebSession>>(getWebSessionClass());
	}

	protected void init() {
		super.init();

		getSecuritySettings().setAuthorizationStrategy(new RoleAuthorizationStrategy(this));
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(this);
	}

	public final boolean hasAnyRole(Roles roles) {
		Roles sessionRoles = AbstractAuthenticatedWebSession.get().getRoles();
		return ((sessionRoles != null) && (sessionRoles.hasAnyRole(roles)));
	}

	public final void onUnauthorizedInstantiation(Component component) {
		if (component instanceof Page) {
			if (!(AbstractAuthenticatedWebSession.get().isSignedIn())) {
				restartResponseAtSignInPage();
			} else {
				onUnauthorizedPage((Page) component);
			}
		} else {
			throw new UnauthorizedInstantiationException(component.getClass());
		}
	}

	public void restartResponseAtSignInPage() {
		throw new RestartResponseAtInterceptPageException(getSignInPageClass());
	}

	public Session newSession(Request request, Response response) {
		try {
			return ((Session) ((Class<?>) this.webSessionClassRef.get())
					.getDeclaredConstructor(new Class[]{Request.class}).newInstance(new Object[]{request}));
		} catch (Exception e) {
			throw new WicketRuntimeException("Unable to instantiate web session " + this.webSessionClassRef.get(), e);
		}
	}

	protected void onUnauthorizedPage(Page page) {
		throw new UnauthorizedInstantiationException(page.getClass());
	}
}
