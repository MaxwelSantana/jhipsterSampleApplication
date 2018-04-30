import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ConfiguracaoComponent } from './configuracao.component';
import { ConfiguracaoDetailComponent } from './configuracao-detail.component';
import { ConfiguracaoPopupComponent } from './configuracao-dialog.component';
import { ConfiguracaoDeletePopupComponent } from './configuracao-delete-dialog.component';

export const configuracaoRoute: Routes = [
    {
        path: 'configuracao',
        component: ConfiguracaoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuracaos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'configuracao/:id',
        component: ConfiguracaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuracaos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configuracaoPopupRoute: Routes = [
    {
        path: 'configuracao-new',
        component: ConfiguracaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'configuracao/:id/edit',
        component: ConfiguracaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'configuracao/:id/delete',
        component: ConfiguracaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
