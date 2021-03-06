//
//  ViewController.m
//  WebViewSample
//
//  Created by Kang Jinyeoung on 10/06/2018.
//  Copyright © 2018 SuperbDerrick. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet WKWebView *webView;
@property (weak, nonatomic) IBOutlet UIButton *helloButton;

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  
  
  WKWebViewConfiguration *configuration = [[WKWebViewConfiguration alloc]
                                           init];
  WKUserContentController *controller = [[WKUserContentController alloc]
                                         init];
  [controller addScriptMessageHandler:self name:@"observe"];
  configuration.userContentController = controller;
  
  
  NSURL *path = [[NSBundle mainBundle] URLForResource:@"test" withExtension:@"html"];
  NSURLRequest *request=[NSURLRequest requestWithURL:path];
  
  [self.webView loadRequest:request];
  
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  
}

- (void)userContentController:(WKUserContentController *)userContentController
      didReceiveScriptMessage:(WKScriptMessage *)message {
}



- (IBAction)clickedSayHello:(id)sender {
  NSLog(@"hello");
}


@end

