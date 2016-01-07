/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package net.tiger.star.nsen.action;

import javax.annotation.Resource;

import net.tiger.star.nsen.service.NsenUserService;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

/**
 * ログアウト
 * @author admin
 *
 */
public class LogoutAction extends AbstractBaseAction {

    @Resource
    protected NsenUserService nsenUserService;

    @Execute(validator = false, redirect=true)
    @RemoveSession(name="userDataDto")
    public String index() {
        // チケット情報クリア
        clearTicket();
        return "/";
    }
}
