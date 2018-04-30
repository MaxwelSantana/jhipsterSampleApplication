import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Configuracao } from './configuracao.model';
import { ConfiguracaoPopupService } from './configuracao-popup.service';
import { ConfiguracaoService } from './configuracao.service';

@Component({
    selector: 'jhi-configuracao-delete-dialog',
    templateUrl: './configuracao-delete-dialog.component.html'
})
export class ConfiguracaoDeleteDialogComponent {

    configuracao: Configuracao;

    constructor(
        private configuracaoService: ConfiguracaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configuracaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configuracaoListModification',
                content: 'Deleted an configuracao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-configuracao-delete-popup',
    template: ''
})
export class ConfiguracaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configuracaoPopupService: ConfiguracaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.configuracaoPopupService
                .open(ConfiguracaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
