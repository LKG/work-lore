<!--- --->
                    <div class="m-account__head">
                        <div class="m-account__head__default" >
                            <div class="m-account__head__default-inner g-pointer">
                                <svg aria-hidden="true" class="svg-icon svg-default-avatar">
                                    <use xlink:href="#svg-default-avatar"></use>
                                </svg>
                            </div>
                        <@shiro.guest>
                          <p class="m-account__banner" >
                              <ul class="header-right">
                                  <li class=""> <a href="${appHost}/login.jhtml">登录</a>
                                  </li>
                                  <li class="shu"></li>
                                  <li class="">
                                      <a  class="red" href="${appHost}/regist.jhtml" target="_blank" tabindex="8">注册</a>
                                  </li>
                              </ul>
                          </p>
                        </@shiro.guest>
                        </div>
                    </div>
                    <!--- --->